package com.programming.kaldiweather.util

import android.app.Activity
import android.content.Intent
import android.provider.Settings

object IntentUtils {
    fun openSettings(activity: Activity) {
        activity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }
}