package com.programming.kaldiweather.di

import com.programming.kaldiweather.repository.SearchHistoryRepository
import com.programming.kaldiweather.repository.SuggestionRepository
import com.programming.kaldiweather.repository.searchistory.SearchHistoryRepositoryImpl
import com.programming.kaldiweather.repository.suggestion.SuggestionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun providesSuggestionRepository(): SuggestionRepository = SuggestionRepositoryImpl()

    @Provides
    @Singleton
    fun providesSearchHistoryRepository(): SearchHistoryRepository = SearchHistoryRepositoryImpl()
}