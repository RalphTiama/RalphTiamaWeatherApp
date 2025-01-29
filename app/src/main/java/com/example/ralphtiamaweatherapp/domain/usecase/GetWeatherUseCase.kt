package com.example.ralphtiamaweatherapp.domain.usecase

import com.example.ralphtiamaweatherapp.data.WeatherRepository
import com.example.ralphtiamaweatherapp.domain.model.WeatherResponse
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend fun execute(lat: Double, lon: Double, apiKey: String): WeatherResponse {
        return weatherRepository.fetchWeather(lat, lon, apiKey)
    }

}