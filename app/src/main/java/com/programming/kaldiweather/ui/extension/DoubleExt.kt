package com.programming.kaldiweather.ui.extension

import java.util.Locale

fun Double.toHumanReadableValue(locale: Locale? = null, unit: String): String {
    return String.format(locale = locale, format = "%.2f%s", this, unit)
}