package com.programming.kaldiweather.repository.suggestion

import com.programming.kaldiweather.repository.SuggestionRepository
import javax.inject.Inject

class SuggestionRepositoryImpl @Inject constructor() : SuggestionRepository {

    private val latestInMemorySuggestedCityList = mutableListOf<String>()

    override fun getLatestInMemorySuggestedCityList(): List<String> {
        val list = listOf(
            "New York", "London", "Tokyo", "Paris", "Sydney", "Berlin",
            "Rome", "Dubai", "Toronto", "Singapore", "Beijing", "Mumbai",
            "Los Angeles", "Moscow", "Cape Town", "Bangkok", "Istanbul", "Barcelona",
            "Seoul", "Rio de Janeiro", "Chicago", "Shanghai", "Madrid", "Hong Kong",
            "Mexico City", "Vienna", "Amsterdam", "Kuala Lumpur", "Cairo", "Buenos Aires",
            "Dublin", "Melbourne", "Lisbon", "Prague", "Warsaw", "Helsinki",
            "Copenhagen", "Oslo", "Stockholm", "Zurich", "Edinburgh", "Athens",
            "Brussels", "Venice", "Florence", "Santiago", "Montreal", "Jakarta"
        )
        latestInMemorySuggestedCityList.addAll(list)
        return latestInMemorySuggestedCityList
    }

    override fun addSuggestionIntoInMemorySuggestedCityList(suggestion: String) {
        if (!latestInMemorySuggestedCityList.contains(suggestion)) {
            latestInMemorySuggestedCityList.add(suggestion)
        }
    }

    override fun clearInMemorySuggestedCityList() {
        latestInMemorySuggestedCityList.clear()
    }
}