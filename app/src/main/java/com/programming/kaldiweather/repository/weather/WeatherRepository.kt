package com.programming.kaldiweather.repository.weather

import android.util.Log
import com.programming.kaldiweather.network.WeatherService
import com.programming.kaldiweather.ui.domain.model.City
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
        }.getOrNull()?.results?.firstOrNull()?.let { result ->
            City(
                id = result.id,
                name = result.name,
                latitude = result.latitude,
                longitude = result.longitude,
                country = result.country
            )
        }
    }
}

