package com.programming.kaldiweather.network.model

import com.squareup.moshi.Json

data class ForecastResponse(
    @Json(name = "hourly")
    val hourlyForecast: HourlyForecast
)

data class HourlyForecast(
    @Json(name = "temperature_2m")
    val temperature2m: List<Double>,
    @Json(name = "relative_humidity_2m")
    val relativeHumidity2m: List<Long>,
    @Json(name = "apparent_temperature")
    val apparentTemperature: List<Double>
)