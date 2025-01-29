package com.example.ralphtiamaweatherapp.ui.screens

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ralphtiamaweatherapp.AuthState
import com.example.ralphtiamaweatherapp.AuthViewModel
import com.example.ralphtiamaweatherapp.AuthenticatedRoute
import com.example.ralphtiamaweatherapp.LoginScreenRoute
import com.example.ralphtiamaweatherapp.presentation.WeatherViewModel
import com.example.ralphtiamaweatherapp.ui.screens.listweather.ItemLastViewedWeather
import com.example.ralphtiamaweatherapp.ui.screens.listweather.ItemLastViewedWeatherPreview
import com.example.ralphtiamaweatherapp.ui.screens.listweather.NavigationTopInfoListWeather
import com.example.ralphtiamaweatherapp.ui.theme.Color4
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun ListOfWeatherScreen(modifier: Modifier = Modifier, navController: NavHostController, authViewModel: AuthViewModel, weatherViewModel: WeatherViewModel){

    val weatherHistory by weatherViewModel.weatherHistory.collectAsState()
    val autState = authViewModel.authState.observeAsState()

    LaunchedEffect(autState.value) {
        when(autState.value){
            is AuthState.UnAuthenticated -> {
                navController.navigate(LoginScreenRoute){popUpTo<AuthenticatedRoute>{inclusive = true}}
            }
            else -> weatherViewModel.fetchWeatherHistory()
        }
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
    ) {


        NavigationTopInfoListWeather()

        Text(text = "Last Viewed Today", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color4)

        LazyColumn(modifier = Modifier.fillMaxHeight()) {

            itemsIndexed(weatherHistory) { _, history ->
                ItemLastViewedWeather(
                    temperature = history.temperature,
                    description = history.description,
                    timestamp = history.dt
                )
            }
        }

    }




}

