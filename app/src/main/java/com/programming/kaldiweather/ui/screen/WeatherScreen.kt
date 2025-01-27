package com.programming.kaldiweather.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.programming.kaldiweather.connection.ConnectivityObserver.ConnectivityStatus

@Composable
fun WeatherScreen(
    onExit: () -> Unit
) {
    val viewModel = hiltViewModel<WeatherViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val suggestionViewState by viewModel.suggestionViewState.collectAsStateWithLifecycle()
    val searchHistoryViewState by viewModel.searchHistoryViewState.collectAsStateWithLifecycle()
    val connectivityStatus by viewModel.connectivityObserver.observe().collectAsStateWithLifecycle(initialValue = ConnectivityStatus.Unavailable)

    if (viewState == WeatherViewState.Exit) {
        onExit()
    }

    BackHandler {
        viewModel.onBack()
    }

    WeatherContent(
        viewState = viewState,
        suggestionViewState = suggestionViewState,
        searchHistoryViewState = searchHistoryViewState,
        connectivityStatus = connectivityStatus,
        onSelectedSuggestion = viewModel::onSearchWeather,
        onSelectedHistory = viewModel::onSearchWeather
    )
}