package com.example.ralphtiamaweatherapp.ui.screens.listweather

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ralphtiamaweatherapp.R
import com.example.ralphtiamaweatherapp.data.local.WeatherHistoryEntity
import com.example.ralphtiamaweatherapp.domain.model.WeatherCondition
import com.example.ralphtiamaweatherapp.ui.screens.currentweather.getTimeOfDay
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun ItemLastViewedWeather(  temperature: Float = 298.80f ,
                             description: String = "few cloudy",
                             timestamp: Long = 1738157048348){

    val temperatureToCelsius = (temperature - 273.15f).toInt()
    val timeOfDay = getTimeOfDay()
    val condition = WeatherCondition.fromDescription(description, timeOfDay)
    val getWeatherIcon = WeatherCondition.getIcon(condition, timeOfDay)

    Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {

        Text(text = getDateFromTimestamp(timestamp))
        Text(description)

        Row {
            val degree = "\u00B0"
            Text("$temperatureToCelsius$degree")
            Image(modifier = Modifier.size(12.dp), painter = painterResource(id = R.drawable.sunny_weather ), contentDescription = "Sun")
        }

    }




}


fun getItemDateFromTimestamp(timestamp: Long): String {
    val dateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Instant.ofEpochSecond(timestamp)
            .atZone(ZoneOffset.ofHours(8))
            .toLocalDateTime()
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    val formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a")
    return dateTime.format(formatter)
}


@Composable
@Preview(showBackground = true)
fun ItemLastViewedWeatherPreview(){
    ItemLastViewedWeather()
}