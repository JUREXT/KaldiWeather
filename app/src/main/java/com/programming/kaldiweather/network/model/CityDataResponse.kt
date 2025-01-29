package com.programming.kaldiweather.network.model

data class CityResult(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String
)

data class CityDataResponse(
    val results: List<CityResult>
)