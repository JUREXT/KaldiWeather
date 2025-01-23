package com.programming.kaldiweather.ui.screen

sealed interface WeatherViewState {
    data object Idle : WeatherViewState
    data object Loading : WeatherViewState
    data class Success(val value: String) : WeatherViewState
    data object Error : WeatherViewState
}