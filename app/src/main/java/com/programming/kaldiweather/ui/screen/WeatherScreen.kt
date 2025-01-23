package com.programming.kaldiweather.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WeatherScreen(
    onExit: () -> Unit
) {
    val viewModel = hiltViewModel<WeatherViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val suggestionViewState by viewModel.suggestionViewState.collectAsStateWithLifecycle()

    if (viewState == WeatherViewState.Exit) {
        onExit()
    }

    BackHandler {
        viewModel.onBack()
    }

    WeatherContent(
        viewState = viewState,
        suggestionViewState = suggestionViewState,
        onSelectedSuggestion = viewModel::onSearchWeather,
        onExitClick = viewModel::onBack
    )
}