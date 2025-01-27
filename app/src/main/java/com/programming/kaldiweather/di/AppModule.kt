package com.programming.kaldiweather.di

import android.app.Application
import android.content.Context
import com.programming.kaldiweather.connection.ConnectivityObserver
import com.programming.kaldiweather.connection.ConnectivityObserverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesApplicationContext(@ApplicationContext application: Context): Context = application

    @OptIn(ExperimentalCoroutinesApi::class)
    @Singleton
    @Provides
    fun providesConnectivityObserver(application: Application): ConnectivityObserver {
        return ConnectivityObserverImpl(application = application)
    }
}