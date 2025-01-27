package com.programming.kaldiweather.ui.screen

import com.programming.kaldiweather.ui.domain.model.Forecast

sealed interface WeatherViewState {
    data object Idle : WeatherViewState
    data object Loading : WeatherViewState
    data object Exit : WeatherViewState
    data class Success(val forecast: Forecast) : WeatherViewState
    data object Error : WeatherViewState
}

sealed interface SuggestionViewState {
    data class Suggestions(val suggestions: List<String> = emptyList()) : SuggestionViewState
}

sealed interface SearchHistoryViewState {
    data class History(val history: List<String> = emptyList()) : SearchHistoryViewState
}