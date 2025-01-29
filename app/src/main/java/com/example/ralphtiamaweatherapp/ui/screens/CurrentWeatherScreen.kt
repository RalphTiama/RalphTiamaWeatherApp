package com.example.ralphtiamaweatherapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ralphtiamaweatherapp.AuthState
import com.example.ralphtiamaweatherapp.AuthViewModel
import com.example.ralphtiamaweatherapp.AuthenticatedRoute
import com.example.ralphtiamaweatherapp.LoginScreenRoute
import com.example.ralphtiamaweatherapp.presentation.WeatherViewModel
import com.example.ralphtiamaweatherapp.ui.screens.currentweather.CardTemperature
import com.example.ralphtiamaweatherapp.ui.screens.currentweather.CardWeatherDetails
import com.example.ralphtiamaweatherapp.ui.screens.currentweather.NavigationTopInfo
import com.example.ralphtiamaweatherapp.ui.screens.currentweather.TodayForecast
import com.example.ralphtiamaweatherapp.ui.theme.Color4
import com.example.ralphtiamaweatherapp.ui.theme.Color9
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    weatherViewModel: WeatherViewModel
) {
    val autState = authViewModel.authState.observeAsState()
    val weatherData by weatherViewModel.weatherData.collectAsState()


    LaunchedEffect(autState.value) {
        when(autState.value){
            is AuthState.UnAuthenticated -> navController.navigate(LoginScreenRoute){popUpTo<AuthenticatedRoute>{inclusive = true}}
            else -> Unit
        }
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        weatherData?.let{weatherData ->

            val temperatureToCelsius = (weatherData.main.temp - 273.15f).toInt()

            Spacer(modifier = Modifier.height(16.dp))

            NavigationTopInfo(
                modifier = modifier.statusBarsPadding(),
                cityName = weatherData.name,
                country = weatherData.sys.country,
                date = weatherData.dt
            )

            Spacer(modifier = Modifier.height(24.dp))

            CardTemperature(
                temperature = temperatureToCelsius.toString(),
                weatherCondition = weatherData.weather[0].description,
            )

            Spacer(modifier = Modifier.height(14.dp))

            Card(elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color9
                ),
                shape = RoundedCornerShape(18.dp)
            ) {

                val sunriseTime = convertTimestampToTime(weatherData.sys.sunrise, weatherData.timezone)
                val sunsetTime = convertTimestampToTime(weatherData.sys.sunset, weatherData.timezone)

                CardWeatherDetails(
                    sunrise = sunriseTime,
                    sunset = sunsetTime,
                    humidity = weatherData.main.humidity.toString(),
                    windSpeed = weatherData.wind.speed.toString()
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            TodayForecast()


        }




    }

}



@RequiresApi(Build.VERSION_CODES.O)
fun convertTimestampToTime(timestamp: Long, timezoneOffset: Int): String {
    val instant = Instant.ofEpochSecond(timestamp)
    val zoneOffset = ZoneOffset.ofTotalSeconds(timezoneOffset)
    val dateTime = ZonedDateTime.ofInstant(instant, zoneOffset)
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return dateTime.format(formatter)
}