package com.example.ralphtiamaweatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherHistory(weather: WeatherHistoryEntity)

    @Query("SELECT * FROM weather_history ORDER BY dt DESC")
    suspend fun getAllWeatherHistory(): List<WeatherHistoryEntity>

}