package com.programming.kaldiweather.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

) : ViewModel() {

    private val _viewState: MutableStateFlow<WeatherViewState> = MutableStateFlow(WeatherViewState.Idle)
    val viewState = _viewState.asStateFlow()

    private var job: Job? = null

    fun onSearchWeather(cityName: String) {
        job?.cancel()
        job = viewModelScope.launch {
            _viewState.update { WeatherViewState.Loading }
            delay(1500)
            _viewState.update { WeatherViewState.Exit }
        }
    }


    fun onBack() {
        job?.cancel()
        _viewState.update { WeatherViewState.Exit }
    }
}