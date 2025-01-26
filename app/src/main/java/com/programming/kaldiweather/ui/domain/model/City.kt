package com.programming.kaldiweather.ui.domain.model

data class City(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String
)