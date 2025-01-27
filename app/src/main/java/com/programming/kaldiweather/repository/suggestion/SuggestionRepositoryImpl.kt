package com.programming.kaldiweather.repository.suggestion

import com.programming.kaldiweather.repository.SuggestionRepository
import javax.inject.Inject

class SuggestionRepositoryImpl @Inject constructor() : SuggestionRepository {

    override fun getSuggestedCityList(): List<String> {
        return listOf(
            "New York", "London", "Tokyo", "Paris", "Sydney", "Berlin",
            "Rome", "Dubai", "Toronto", "Singapore", "Beijing", "Mumbai",
            "Los Angeles", "Moscow", "Cape Town", "Bangkok", "Istanbul", "Barcelona",
            "Seoul", "Rio de Janeiro", "Chicago", "Shanghai", "Madrid", "Hong Kong",
            "Mexico City", "Vienna", "Amsterdam", "Kuala Lumpur", "Cairo", "Buenos Aires",
            "Dublin", "Melbourne", "Lisbon", "Prague", "Warsaw", "Helsinki",
            "Copenhagen", "Oslo", "Stockholm", "Zurich", "Edinburgh", "Athens",
            "Brussels", "Venice", "Florence", "Santiago", "Montreal", "Jakarta"
        )
    }
}