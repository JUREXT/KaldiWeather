package com.programming.kaldiweather.network.extension

import com.programming.kaldiweather.network.model.ForecastResponse
import com.programming.kaldiweather.ui.domain.model.Forecast

fun ForecastResponse.toForecast(): Forecast {
    return Forecast(
        temperature = this.hourlyForecast.temperature2m.average(),
        temperatureMax = this.hourlyForecast.temperature2m.max(),
        relativeHumidity = this.hourlyForecast.relativeHumidity2m.average(),
        feelsLikeTemperature = this.hourlyForecast.apparentTemperature.average()
    )
}