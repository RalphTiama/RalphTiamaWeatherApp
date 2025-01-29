package com.example.ralphtiamaweatherapp.domain.usecase

import com.example.ralphtiamaweatherapp.data.WeatherRepository
import com.example.ralphtiamaweatherapp.data.local.WeatherHistoryEntity
import javax.inject.Inject

class GetWeatherHistoryUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun execute(): List<WeatherHistoryEntity> {
        return weatherRepository.getWeatherHistory()
    }
}