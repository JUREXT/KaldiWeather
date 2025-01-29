package com.programming.kaldiweather.ui.domain.model

data class Weather(
    val city: String,
    val date: String,
    val conditionText: String? = null,
    val conditionIcon: String? = null,
    val maxtemp_c: Double? = null,
    val mintemp_c: Double? = null
)