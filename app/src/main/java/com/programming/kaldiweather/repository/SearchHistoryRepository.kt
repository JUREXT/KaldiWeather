package com.programming.kaldiweather.repository

interface SearchHistoryRepository {
    fun getLatestInMemorySearchCityList(): List<String>
    fun addIntoInMemorySearchCityList(city: String)
    fun clearInMemorySearchCityList()
}