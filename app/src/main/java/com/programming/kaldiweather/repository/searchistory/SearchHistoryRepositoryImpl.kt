package com.programming.kaldiweather.repository.searchistory

import com.programming.kaldiweather.repository.SearchHistoryRepository
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor() : SearchHistoryRepository {

    private val searchHistoryList = mutableListOf<String>()

    override fun getLatestInMemorySearchCityList(): List<String> = searchHistoryList

    override fun addIntoInMemorySearchCityList(city: String) {
        if (city in searchHistoryList) {
            return
        }
        if (searchHistoryList.size == LIST_SIZE_LIMIT) {
            val firstElement = searchHistoryList.first()
            searchHistoryList.remove(firstElement)
        }
        searchHistoryList.add(city)
    }

    override fun clearInMemorySearchCityList() {
        searchHistoryList.clear()
    }

    companion object {
        const val LIST_SIZE_LIMIT = 5
    }
}