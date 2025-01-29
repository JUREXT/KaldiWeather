package com.programming.kaldiweather.repository.weather

import android.util.Log
import com.programming.kaldiweather.network.WeatherService
import com.programming.kaldiweather.network.extension.toCity
import com.programming.kaldiweather.network.extension.toForecast
import com.programming.kaldiweather.ui.domain.model.City
import com.programming.kaldiweather.ui.domain.model.Forecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {
    suspend fun getCityData(cityName: String): City? = withContext(Dispatchers.IO) {
        runCatching {
            weatherService.getCityData(cityName = cityName)
        }.onFailure { e ->
            Log.d("WeatherRepository", "Error fetching city data: ${e.message}")
        }.getOrNull()?.toCity()
    }

    suspend fun getForecast(latitude: Double, longitude: Double): Forecast? =
        withContext(Dispatchers.IO) {
            runCatching {
                weatherService.getWeatherForecast(latitude = latitude, longitude = longitude)
            }.onFailure { e ->
                Log.d("WeatherRepository", "Error fetching weather forecast: ${e.message}")
            }.getOrNull()?.toForecast()
        }
}