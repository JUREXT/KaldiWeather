package com.programming.kaldiweather.network

import com.programming.kaldiweather.config.Config
import com.programming.kaldiweather.network.model.CityDataResponse
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
}