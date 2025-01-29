package com.example.ralphtiamaweatherapp.di

import com.example.ralphtiamaweatherapp.data.WeatherRepository
import com.example.ralphtiamaweatherapp.data.local.WeatherDao
import com.example.ralphtiamaweatherapp.data.remote.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApiService: WeatherApiService,
        weatherDao: WeatherDao
    ): WeatherRepository {
        return WeatherRepository(weatherApiService, weatherDao)
    }


}