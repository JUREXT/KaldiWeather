package com.programming.kaldiweather.repository

interface SuggestionRepository {
    fun getLatestInMemorySuggestedCityList(): List<String>
    fun addSuggestionIntoInMemorySuggestedCityList(suggestion: String)
    fun clearInMemorySuggestedCityList()
}