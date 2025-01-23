package com.programming.kaldiweather.ui.screen

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

    WeatherContent(
        viewState = viewState,
        onExitClick = onExit
    )
}