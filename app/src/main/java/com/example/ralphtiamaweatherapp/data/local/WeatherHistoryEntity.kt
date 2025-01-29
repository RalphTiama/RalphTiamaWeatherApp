package com.example.ralphtiamaweatherapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_history")
data class WeatherHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cityName: String,
    val temperature: Float,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val sunrise: Long,
    val sunset: Long,
    val dt: Long
)