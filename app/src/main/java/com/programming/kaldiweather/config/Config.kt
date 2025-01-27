package com.programming.kaldiweather.config

object Config {
    const val BASE_URL = "https://geocoding-api.open-meteo.com"
    private const val API_VERSION_1 = BASE_URL.plus("/v1")

    const val API_V1_SEARCH_CITY = API_VERSION_1.plus("/search")
    const val API_V1_WEATHER_FORECAST = "https://api.open-meteo.com/v1/forecast"

    const val API_RESPONSE_COUNT = 1
    const val API_RESPONSE_LANGUAGE = "en"
    const val API_RESPONSE_FORMAT_JSON = "json"
}