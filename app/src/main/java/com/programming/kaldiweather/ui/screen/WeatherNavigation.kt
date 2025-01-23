package com.programming.kaldiweather.ui.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.programming.kaldiweather.ui.NavRoutes

fun NavGraphBuilder.weatherScreen(
    onExit: () -> Unit
) {
    composable<NavRoutes.WeatherRoute> {
        WeatherScreen(
            onExit = onExit
        )
    }
}