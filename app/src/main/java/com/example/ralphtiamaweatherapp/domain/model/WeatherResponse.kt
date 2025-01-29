package com.example.ralphtiamaweatherapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("coord") val coord: Coord,
    @SerialName("weather") val weather: List<Weather>,
    @SerialName("base") val base: String,
    @SerialName("main") val main: Main,
    @SerialName("visibility") val visibility: Int,
    @SerialName("wind") val wind: Wind,
    @SerialName("clouds") val clouds: Clouds,
    @SerialName("dt") val dt: Long,
    @SerialName("sys") val sys: Sys,
    @SerialName("timezone") val timezone: Int,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("cod") val cod: Int
)

@Serializable
data class Coord(
    @SerialName("lon") val lon: Double,
    @SerialName("lat") val lat: Double
)

@Serializable
data class Weather(
    @SerialName("id") val id: Int,
    @SerialName("main") val main: String,
    @SerialName("description") val description: String,
    @SerialName("icon") val icon: String
)

@Serializable
data class Main(
    @SerialName("temp") val temp: Float,
    @SerialName("feels_like") val feelsLike: Float,
    @SerialName("temp_min") val tempMin: Float,
    @SerialName("temp_max") val tempMax: Float,
    @SerialName("pressure") val pressure: Int,
    @SerialName("humidity") val humidity: Int,
    @SerialName("sea_level") val seaLevel: Int,
    @SerialName("grnd_level") val grndLevel: Int
)

@Serializable
data class Wind(
    @SerialName("speed") val speed: Float,
    @SerialName("deg") val deg: Int,
    @SerialName("gust") val gust: Float = 0.0f
)

@Serializable
data class Clouds(
    @SerialName("all") val all: Int
)

@Serializable
data class Sys(
    @SerialName("type") val type: Int,
    @SerialName("id") val id: Int,
    @SerialName("country") val country: String,
    @SerialName("sunrise") val sunrise: Long,
    @SerialName("sunset") val sunset: Long
)