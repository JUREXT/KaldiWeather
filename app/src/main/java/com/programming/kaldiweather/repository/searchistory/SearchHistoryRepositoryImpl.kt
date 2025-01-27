package com.programming.kaldiweather.repository.searchistory

import com.programming.kaldiweather.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor() : SearchHistoryRepository {

    private val searchHistoryFlow = MutableStateFlow<List<String>>(emptyList())

    override fun getHistorySearchedCityFlow(): StateFlow<List<String>> {
        return searchHistoryFlow.asStateFlow()
    }

    override fun addToCityHistorySearched(city: String) {
        searchHistoryFlow.update { currentList ->
            if (city in currentList) return@update currentList
            val updatedList = currentList.toMutableList()
            if (updatedList.size == LIST_SIZE_LIMIT) {
                updatedList.removeAt(0)
            }
            updatedList.add(city)
            updatedList
        }
    }

    companion object {
        private const val LIST_SIZE_LIMIT = 5
    }
}
