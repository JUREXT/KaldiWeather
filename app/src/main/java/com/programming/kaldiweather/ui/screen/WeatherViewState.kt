package com.programming.kaldiweather.ui.screen

import com.programming.kaldiweather.ui.domain.model.Weather

sealed interface WeatherViewState {
    data object Idle : WeatherViewState
    data object Loading : WeatherViewState
    data object Exit : WeatherViewState
    data class Success(val weather: Weather) : WeatherViewState
    data object Error : WeatherViewState
}

sealed interface SuggestionViewState {
    data object Idle : SuggestionViewState
    data object Loading : SuggestionViewState
    data object Error : SuggestionViewState
    data class Success(val suggestions: List<String>) : SuggestionViewState
}

sealed interface SearchHistoryViewState {
    data object Idle : SearchHistoryViewState
    data object NoData : SearchHistoryViewState
    data class Data(val history: List<String>) : SearchHistoryViewState
}