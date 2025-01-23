package com.programming.kaldiweather.ui.screen

sealed interface WeatherViewState {
    data object Idle : WeatherViewState
    data object Loading : WeatherViewState
    data object Exit : WeatherViewState
    data class Success(val value: String) : WeatherViewState
    data object Error : WeatherViewState
}

sealed interface SuggestionViewState {
    data object Idle : SuggestionViewState
    data object Loading : SuggestionViewState
    data object Error : SuggestionViewState
    data class Success(val suggestions: List<String>) : SuggestionViewState
}