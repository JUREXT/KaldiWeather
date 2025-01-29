package com.programming.kaldiweather.network

import com.programming.kaldiweather.config.Config
import com.programming.kaldiweather.network.model.CityDataResponse
import com.programming.kaldiweather.network.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET(Config.API_V1_SEARCH_CITY)
    suspend fun getCityData(
        @Query("name") cityName: String,
        @Query("count") count: Int = Config.API_RESPONSE_COUNT,
        @Query("language") language: String = Config.API_RESPONSE_LANGUAGE,
        @Query("format") format: String = Config.API_RESPONSE_FORMAT_JSON
    ): CityDataResponse

    @GET(Config.API_V1_WEATHER_FORECAST)
    suspend fun getWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m,relative_humidity_2m,apparent_temperature"
    ): ForecastResponse
}