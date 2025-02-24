package com.programming.kaldiweather.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programming.kaldiweather.connection.ConnectivityObserver
import com.programming.kaldiweather.location.LocationProvider
import com.programming.kaldiweather.repository.SearchHistoryRepository
import com.programming.kaldiweather.repository.SuggestionRepository
import com.programming.kaldiweather.repository.weather.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val suggestionRepository: SuggestionRepository,
    private val searchHistoryRepository: SearchHistoryRepository,
    private val weatherRepository: WeatherRepository,
    private val locationProvider: LocationProvider,
    val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _viewState: MutableStateFlow<WeatherViewState> = MutableStateFlow(WeatherViewState.Idle)
    val viewState = _viewState.asStateFlow()

    private val _suggestionViewState: MutableStateFlow<SuggestionViewState> = MutableStateFlow(SuggestionViewState.Suggestions())
    val suggestionViewState = _suggestionViewState.asStateFlow()

    private val _searchHistoryViewState: MutableStateFlow<SearchHistoryViewState> = MutableStateFlow(SearchHistoryViewState.History())
    val searchHistoryViewState = _searchHistoryViewState.asStateFlow()

    private var job: Job? = null

    init {
        _suggestionViewState.update {
            SuggestionViewState.Suggestions(suggestions = suggestionRepository.getSuggestedCityList())
        }

        searchHistoryRepository.getHistorySearchedCityFlow()
            .onEach { list ->
                _searchHistoryViewState.update {
                    SearchHistoryViewState.History(history = list)
                }
            }.launchIn(viewModelScope)
    }

    fun onSearchWeather(cityName: String) {
        if(connectivityObserver.isConnected() == ConnectivityObserver.ConnectivityStatus.Unavailable) {
            return
        }

        job?.cancel()
        job = viewModelScope.launch {
            _viewState.update { WeatherViewState.Loading }
            val city = weatherRepository.getCityData(cityName = cityName)
            if (city == null) {
                _viewState.update { WeatherViewState.Error }
            } else {
                getForecast(cityName = cityName, latitude = city.latitude, longitude = city.longitude)
            }
        }
    }

    private fun getForecast(cityName: String? = null, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _viewState.update { WeatherViewState.Loading } 
            val forecast = weatherRepository.getForecast(
                latitude = latitude,
                longitude = longitude
            )
            if (forecast == null) {
                _viewState.update { WeatherViewState.Error }
            } else {
                cityName?.let {
                    searchHistoryRepository.addToCityHistorySearched(city = it)
                }
                _viewState.update { WeatherViewState.Success(forecast = forecast) }
            }
        }
    }

    fun onGetLocation() {
        locationProvider.getLastKnownLocation(
            onSuccess = { latitude, longitude ->
                getForecast(latitude = latitude, longitude = longitude)
            },
            onFailure = { e ->
                e.printStackTrace()
            }
        )
    }

    fun onBack() {
        job?.cancel()
        _viewState.update { WeatherViewState.Exit }
    }
}