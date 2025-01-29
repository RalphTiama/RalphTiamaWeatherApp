package com.example.ralphtiamaweatherapp.ui.screens.currentweather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ralphtiamaweatherapp.R
import com.example.ralphtiamaweatherapp.domain.model.TimeOfDay
import com.example.ralphtiamaweatherapp.domain.model.WeatherCondition
import com.example.ralphtiamaweatherapp.ui.theme.Color10
import com.example.ralphtiamaweatherapp.ui.theme.Color4
import com.example.ralphtiamaweatherapp.ui.theme.Color6
import com.example.ralphtiamaweatherapp.ui.theme.Color9
import java.util.Calendar

@Composable
fun CardTemperature(
    modifier: Modifier = Modifier,
    temperature: String = "30",
    weatherCondition: String = "Mostly sunny",
){
    val timeOfDay = getTimeOfDay()
    val condition = WeatherCondition.fromDescription(weatherCondition, timeOfDay)
    val getWeatherIcon = WeatherCondition.getIcon(condition, timeOfDay)

    Box{

        Row(modifier = Modifier.fillMaxWidth()){

            Spacer(modifier = Modifier.width(54.dp))

            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 12.dp),
                shape = RoundedCornerShape(24.dp),
                modifier = modifier.padding(end = 38.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color4)
                ) {

                Column(modifier = modifier.fillMaxWidth().padding(start = 24.dp, top = 16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    val offset = Offset(5.0f, 31.0f)
                    val degree = "\u00B0"

                    Text(
                        text = "$temperature$degree",
                        color = Color9,
                        style = TextStyle(
                            fontSize = 120.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            shadow = Shadow(
                                color = Color10, offset = offset, blurRadius = 6f
                            )
                        )
                    )

                    Text(
                        text = weatherCondition,
                        color = Color6,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily.SansSerif,
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                }
            }




        }


        Column {
            Spacer(modifier = Modifier.height(52.dp))

            Image(
                painter = painterResource(id = getWeatherIcon),
                contentDescription = "Weather Background",
                modifier = Modifier
                    .wrapContentSize(unbounded = true, align = Alignment.BottomStart), contentScale = FixedScale(0.5f)
            )
        }




    }
}

fun getTimeOfDay(): TimeOfDay {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    return if (currentHour in 6..18) {
        TimeOfDay.DAY
    } else {
        TimeOfDay.NIGHT
    }
}


@Composable
@Preview(showBackground = true)
fun TemperatureCardPreview(){
    CardTemperature()
}