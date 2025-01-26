package com.programming.kaldiweather.ui.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("No Activity found.")
}

@SuppressLint("MissingPermission")
fun Context.isConnectedToInternet(): Boolean {
    return getSystemService<ConnectivityManager>()?.run {
        activeNetwork ?: return false
        getNetworkCapabilities(activeNetwork)?.run {
            hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } ?: false
    } ?: false
}