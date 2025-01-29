package com.example.ralphtiamaweatherapp.ui.screens.currentweather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ralphtiamaweatherapp.R
import com.example.ralphtiamaweatherapp.ui.theme.Color4

@Composable
fun TodayForecast(){

    Column {
        Text(text = "Today Forecast", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color4)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            repeat(14) {
                Image(
                    painter = painterResource(id = R.drawable.dummy_image),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)

                )
            }
        }
    }



}

@Composable
@Preview(showBackground = true)
fun TodayForecastPreview(){
    TodayForecast()
}