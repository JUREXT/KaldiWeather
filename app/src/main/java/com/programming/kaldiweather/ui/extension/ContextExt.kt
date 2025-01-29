package com.programming.kaldiweather.ui.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.location.LocationManager
import androidx.core.content.getSystemService

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("No Activity found.")
}

fun Context.isLocationEnabled(): Boolean {
    val locationManager = this.getSystemService<LocationManager>() ?: return false
    return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}