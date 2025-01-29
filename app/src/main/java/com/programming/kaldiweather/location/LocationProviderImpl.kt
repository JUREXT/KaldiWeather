package com.programming.kaldiweather.location

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class LocationProviderImpl @Inject constructor(
    private val client: FusedLocationProviderClient
) : LocationProvider {

    @SuppressLint("MissingPermission") // Is handled separately.
    override fun getLastKnownLocation(
        onSuccess: (latitude: Double, longitude: Double) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val locationTask: Task<Location> = client.lastLocation
        locationTask.addOnSuccessListener { location ->
            if (location != null) {
                onSuccess(location.latitude, location.longitude)
            } else {
                onFailure(Exception("Location not available"))
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)
        }
    }
}