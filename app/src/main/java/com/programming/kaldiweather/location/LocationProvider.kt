package com.programming.kaldiweather.location

interface LocationProvider {
    fun getLastKnownLocation(
        onSuccess: (latitude: Double, longitude: Double) -> Unit,
        onFailure: (Exception) -> Unit
    )
}