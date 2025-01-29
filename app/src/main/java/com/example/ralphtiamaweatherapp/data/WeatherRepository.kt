package com.example.ralphtiamaweatherapp.data

import com.example.ralphtiamaweatherapp.data.local.WeatherDao
import com.example.ralphtiamaweatherapp.data.local.WeatherHistoryEntity
import com.example.ralphtiamaweatherapp.data.remote.WeatherApiService
import com.example.ralphtiamaweatherapp.domain.model.WeatherResponse
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDao: WeatherDao
) {

    suspend fun fetchWeather(lat: Double, lon: Double, apiKey: String): WeatherResponse {

        val response = weatherApiService.getWeather(lat, lon, apiKey)


        val weatherHistory = WeatherHistoryEntity(
            cityName = response.name,
            temperature = response.main.temp,
            description = response.weather.firstOrNull()?.description ?: "No description",
            latitude = response.coord.lat,
            longitude = response.coord.lon,
            sunrise = response.sys.sunrise,
            sunset = response.sys.sunset,
            dt = response.dt
        )

        weatherDao.insertWeatherHistory(weatherHistory)

        return response
    }

    suspend fun getWeatherHistory(): List<WeatherHistoryEntity> {
        return weatherDao.getAllWeatherHistory()
    }
}