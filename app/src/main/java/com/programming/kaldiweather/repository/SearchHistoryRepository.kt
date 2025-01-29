package com.programming.kaldiweather.repository

import kotlinx.coroutines.flow.StateFlow

interface SearchHistoryRepository {
    fun getHistorySearchedCityFlow(): StateFlow<List<String>>
    fun addToCityHistorySearched(city: String)
}