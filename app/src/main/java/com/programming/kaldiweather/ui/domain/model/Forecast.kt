package com.programming.kaldiweather.ui.domain.model

data class Forecast(
    val temperature: Double,
    val temperatureMax: Double,
    val feelsLikeTemperature: Double,
    val relativeHumidity: Double
)