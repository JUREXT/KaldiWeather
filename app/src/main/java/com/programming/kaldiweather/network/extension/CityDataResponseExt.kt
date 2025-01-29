package com.programming.kaldiweather.network.extension

import com.programming.kaldiweather.network.model.CityDataResponse
import com.programming.kaldiweather.ui.domain.model.City

fun CityDataResponse.toCity(): City? {
    return this.results.firstOrNull()?.let { result ->
        City(
            id = result.id,
            name = result.name,
            latitude = result.latitude,
            longitude = result.longitude,
            country = result.country
        )
    }
}