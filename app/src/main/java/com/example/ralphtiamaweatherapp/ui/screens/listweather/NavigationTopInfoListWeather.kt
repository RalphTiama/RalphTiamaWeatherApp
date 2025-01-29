package com.example.ralphtiamaweatherapp.ui.screens.listweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.ralphtiamaweatherapp.AuthViewModel
import com.example.ralphtiamaweatherapp.R
import com.example.ralphtiamaweatherapp.ui.theme.Color4
import com.example.ralphtiamaweatherapp.ui.theme.Color9
import com.example.sneakerups.screens.LoginScreen
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun NavigationTopInfoListWeather(
    modifier: Modifier = Modifier,
    cityName: String = "Manila",
    date: Long = 1738147652 ,
    country: String = "Ph",
    onMenuIconClick: () -> Unit = {},
    onAddIconClick: () -> Unit = {}
){

    Column(modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Icon(painter = painterResource(id = R.drawable.ico_menu), contentDescription = "Drawer")

            Column(modifier = modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = cityName, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color4)
                Text(text =  getDateFromTimestamp(date))
                Text(text = country)

            }

            Icon(modifier = Modifier.size(34.dp), imageVector = Icons.Default.Add, contentDescription = "Drawer")


        }

    }
}


fun getDateFromTimestamp(timestamp: Long): String {
    val dateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Instant.ofEpochSecond(timestamp)
            .atZone(ZoneOffset.ofHours(8)) // Adjust for Manila's timezone (UTC +8)
            .toLocalDateTime()
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    // Format the date as you like (e.g., "Sun, Nov 16")
    val formatter = DateTimeFormatter.ofPattern("E, MMM dd")
    return dateTime.format(formatter)
}


@Composable
@Preview(showBackground = true)
fun NavigationTopInfoListWeatherPreview(){
    NavigationTopInfoListWeather()
}