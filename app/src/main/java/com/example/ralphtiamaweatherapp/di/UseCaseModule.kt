package com.example.ralphtiamaweatherapp.di

import com.example.ralphtiamaweatherapp.data.WeatherRepository
import com.example.ralphtiamaweatherapp.domain.usecase.GetWeatherHistoryUseCase
import com.example.ralphtiamaweatherapp.domain.usecase.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetWeatherUseCase(weatherRepository: WeatherRepository): GetWeatherUseCase {
        return GetWeatherUseCase(weatherRepository)
    }

    @Provides
    @Singleton
    fun provideGetWeatherHistoryUseCase(weatherRepository: WeatherRepository): GetWeatherHistoryUseCase {
        return GetWeatherHistoryUseCase(weatherRepository)
    }
}