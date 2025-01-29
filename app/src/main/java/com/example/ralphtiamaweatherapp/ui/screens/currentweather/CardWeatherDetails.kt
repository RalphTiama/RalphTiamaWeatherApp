package com.example.ralphtiamaweatherapp.ui.screens.currentweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ralphtiamaweatherapp.R
import com.example.ralphtiamaweatherapp.ui.theme.Color4

@Composable
fun CardWeatherDetails(
    modifier: Modifier = Modifier,
    sunrise: String = "5:63 am",
    sunset: String = "5:63 pm",
    humidity: String = "60",
    windSpeed: String = "12"
){
    Column(modifier = modifier.fillMaxWidth().padding(vertical = 14.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Column(modifier = modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Column(modifier = modifier.wrapContentSize(),
                    verticalArrangement = Arrangement.Top,) {

                    Row {
                        Text(text = "Sunrise:",  color =  Color.Gray)
                        Icon(modifier = Modifier.size(16.dp), painter = painterResource(id = R.drawable.ic_sun), tint = Color.Unspecified, contentDescription = "Humidity")
                    }

                    Text(text = sunrise, fontWeight = FontWeight.Bold,color = Color4)

                }
                
                Spacer(modifier = Modifier.height(4.dp))

                Column(modifier = modifier.wrapContentSize(),
                    verticalArrangement = Arrangement.Top,) {

                    Row {
                        Text(text = "Sunrise:",  color =  Color.Gray)
                        Icon(modifier = Modifier.size(16.dp), painter = painterResource(id = R.drawable.ic_half_sun), tint = Color.Unspecified, contentDescription = "Humidity")
                    }

                    Text(text = sunset, fontWeight = FontWeight.Bold,color = Color4)

                }






            }

            Column(modifier = modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = R.drawable.ic_humidity), tint = Color.Unspecified, contentDescription = "Humidity")
                Text(text = "$humidity%", fontWeight = FontWeight.Bold,color = Color4)
                Text(text = "Humidity", color = Color.Gray)

            }

            Column(modifier = modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = R.drawable.ic_wind), tint = Color.Unspecified, contentDescription = "Humidity")
                Text(text = "$windSpeed km/h", fontWeight = FontWeight.Bold,color = Color4)
                Text(text = "Wind Speed", color = Color.Gray)

            }



        }

    }
}

@Composable
@Preview(showBackground = true)
fun CardWeatherDetailsPreview(){
    CardWeatherDetails()
}