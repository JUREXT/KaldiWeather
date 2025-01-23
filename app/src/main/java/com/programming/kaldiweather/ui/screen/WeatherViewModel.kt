package com.programming.kaldiweather.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programming.kaldiweather.repository.SuggestionRepository
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
    private val suggestionRepository: SuggestionRepository
) : ViewModel() {

    private val _viewState: MutableStateFlow<WeatherViewState> = MutableStateFlow(WeatherViewState.Idle)
    val viewState = _viewState.asStateFlow()

    private val _suggestionViewState: MutableStateFlow<SuggestionViewState> = MutableStateFlow(SuggestionViewState.Idle)
    val suggestionViewState = _suggestionViewState.asStateFlow()

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
        }
    }

    fun onSearchWeather(cityName: String) {
        Log.d("WHAT", "onSearchWeather: $cityName")
        job?.cancel()
        job = viewModelScope.launch {
//            _viewState.update { WeatherViewState.Loading }
//            delay(1500)
//            _viewState.update { WeatherViewState.Exit }
        }
    }


    fun onBack() {
        job?.cancel()
        _viewState.update { WeatherViewState.Exit }
    }
}