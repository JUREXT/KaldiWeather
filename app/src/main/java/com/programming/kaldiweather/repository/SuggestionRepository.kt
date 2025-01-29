package com.programming.kaldiweather.repository

interface SuggestionRepository {
    fun getSuggestedCityList(): List<String>
}