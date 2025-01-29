package com.programming.kaldiweather.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes {

    @Serializable
    data object WeatherRoute : NavRoutes()
}