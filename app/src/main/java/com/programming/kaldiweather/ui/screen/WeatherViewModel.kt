package com.programming.kaldiweather.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programming.kaldiweather.repository.SearchHistoryRepository
import com.programming.kaldiweather.repository.SuggestionRepository
import com.programming.kaldiweather.repository.weather.WeatherRepository
import com.programming.kaldiweather.ui.domain.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val suggestionRepository: SuggestionRepository,
    private val searchHistoryRepository: SearchHistoryRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _viewState: MutableStateFlow<WeatherViewState> = MutableStateFlow(WeatherViewState.Idle)
    val viewState = _viewState.asStateFlow()

    private val _suggestionViewState: MutableStateFlow<SuggestionViewState> = MutableStateFlow(SuggestionViewState.Idle)
    val suggestionViewState = _suggestionViewState.asStateFlow()

    private val _searchHistoryViewState: MutableStateFlow<SearchHistoryViewState> = MutableStateFlow(SearchHistoryViewState.Idle)
    val searchHistoryViewState = _searchHistoryViewState.asStateFlow()

    private var job: Job? = null

    init {
        viewModelScope.launch {
            _suggestionViewState.update { SuggestionViewState.Loading }
            delay(4000)
            val suggestions = suggestionRepository.getLatestInMemorySuggestedCityList()
            _suggestionViewState.update {
                if (suggestions.isNotEmpty()) {
                    SuggestionViewState.Success(suggestions = suggestions)
                } else {
                    SuggestionViewState.Error
                }
            }

            val list = listOf("Celje", "Laško", "ljubljana", "Koper", "Celje", "Laško", "ljubljana", "Koper", "Celje", "Laško", "ljubljana", "Koper")
            val history = list //searchHistoryRepository.getLatestInMemorySearchCityList()
            _searchHistoryViewState.update {
                if (history.isNotEmpty()) {
                    SearchHistoryViewState.Data(history = history)
                } else {
                    SearchHistoryViewState.NoData
                }
            }
        }
    }

    fun onSearchWeather(cityName: String) {
        Log.d("WHAT", "onSearchWeather: $cityName")

        viewModelScope.launch {
            val city = weatherRepository.getCityData(cityName = cityName)
            Log.d("WHAT", "City: $city")
        }

        val weather = Weather(
            city = cityName,
            date = "26.01.2025",
            maxtemp_c = 23.33,
            mintemp_c = 2.88
        )

        job?.cancel()
        job = viewModelScope.launch {
            _viewState.update { WeatherViewState.Loading }
            delay(1500)
            _viewState.update { WeatherViewState.Success(weather = weather) }
        }
    }


    fun onBack() {
        job?.cancel()
        _viewState.update { WeatherViewState.Exit }
    }
}