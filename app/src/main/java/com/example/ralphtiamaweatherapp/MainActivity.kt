package com.example.ralphtiamaweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ralphtiamaweatherapp.presentation.WeatherViewModel
import com.example.ralphtiamaweatherapp.ui.theme.RalphTiamaWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: AuthViewModel by viewModels()
        val weatherViewModel: WeatherViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            RalphTiamaWeatherAppTheme {
                MyAppNavigation(authViewModel = viewModel, weatherViewModel = weatherViewModel)
            }
        }
    }
}

