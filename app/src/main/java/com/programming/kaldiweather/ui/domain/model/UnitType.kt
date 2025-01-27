package com.programming.kaldiweather.ui.domain.model

import androidx.annotation.StringRes
import com.programming.kaldiweather.R

enum class UnitType(@StringRes val unitRes: Int) {
    Temperature(unitRes = R.string.unit_temperature),
    Humidity(unitRes = R.string.unit_humidity)
}