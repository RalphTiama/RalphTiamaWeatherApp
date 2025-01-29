package com.example.ralphtiamaweatherapp.domain.model

import com.example.ralphtiamaweatherapp.R

sealed class WeatherCondition {
    data object ClearSky : WeatherCondition()
    data object FewClouds : WeatherCondition()
    data object ScatteredClouds : WeatherCondition()
    data object BrokenClouds : WeatherCondition()
    data object OvercastClouds : WeatherCondition()
    data object ShowerRain : WeatherCondition()
    data object RainThunder : WeatherCondition()
    data object Storm : WeatherCondition()
    data object Snow : WeatherCondition()
    data object Mist : WeatherCondition()

    companion object {
        fun fromDescription(description: String, timeOfDay: TimeOfDay): WeatherCondition {
            return when (description.toLowerCase()) {
                "clear sky" -> ClearSky
                "few clouds" -> FewClouds
                "scattered clouds" -> ScatteredClouds
                "broken clouds" -> BrokenClouds
                "overcast clouds" -> OvercastClouds
                "shower rain" -> ShowerRain
                "rain and thunder" -> RainThunder
                "storm" -> Storm
                "snow" -> Snow
                "mist" -> Mist
                else -> ClearSky
            }
        }

        fun getIcon(condition: WeatherCondition, timeOfDay: TimeOfDay): Int {
            return when (condition) {
                is ClearSky -> if (timeOfDay == TimeOfDay.DAY) R.drawable.sunny_weather else R.drawable.night_weather
                is FewClouds -> if (timeOfDay == TimeOfDay.DAY) R.drawable.sunny_weather else R.drawable.night_weather
                is ScatteredClouds -> if (timeOfDay == TimeOfDay.DAY) R.drawable.sunny_weather else R.drawable.night_weather
                is BrokenClouds -> if (timeOfDay == TimeOfDay.DAY) R.drawable.sunny_weather else R.drawable.night_weather
                is OvercastClouds -> if (timeOfDay == TimeOfDay.DAY) R.drawable.sunny_weather else R.drawable.night_weather
                is ShowerRain -> if (timeOfDay == TimeOfDay.DAY) R.drawable.sunny_weather else R.drawable.night_weather
                is RainThunder -> if (timeOfDay == TimeOfDay.DAY) R.drawable.sunny_weather else R.drawable.night_weather
                is Storm -> if (timeOfDay == TimeOfDay.DAY) R.drawable.sunny_weather else R.drawable.night_weather
                is Snow -> if (timeOfDay == TimeOfDay.DAY) R.drawable.sunny_weather else R.drawable.night_weather
                is Mist -> if (timeOfDay == TimeOfDay.DAY) R.drawable.sunny_weather else R.drawable.night_weather
            }
        }
    }
}

enum class TimeOfDay {
    DAY, NIGHT
}