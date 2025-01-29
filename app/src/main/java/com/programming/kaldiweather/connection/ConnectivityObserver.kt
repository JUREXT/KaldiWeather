package com.programming.kaldiweather.connection

import androidx.annotation.StringRes
import com.programming.kaldiweather.R
import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<ConnectivityStatus>
    fun isConnected(): ConnectivityStatus

    enum class ConnectivityStatus(@StringRes val messageRes: Int) {
        Available(messageRes = R.string.connection_status_available),
        Unavailable(messageRes = R.string.connection_status_unavailable),
        Losing(messageRes = R.string.connection_status_losing),
        Lost(messageRes = R.string.connection_status_lost)
    }
}