package com.example.ralphtiamaweatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ralphtiamaweatherapp.data.local.WeatherHistoryEntity
import com.example.ralphtiamaweatherapp.domain.model.WeatherResponse
import com.example.ralphtiamaweatherapp.domain.usecase.GetWeatherHistoryUseCase
import com.example.ralphtiamaweatherapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherHistoryUseCase: GetWeatherHistoryUseCase
) : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> get() = _weatherData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _weatherHistory = MutableStateFlow<List<WeatherHistoryEntity>>(emptyList())
    val weatherHistory: StateFlow<List<WeatherHistoryEntity>> get() = _weatherHistory

    init {
        // Manila location
        fetchWeather(14.599512, 120.984222, "API_KEY")
        fetchWeatherHistory()
    }


    fun fetchWeather(lat: Double, lon: Double, apiKey: String) = viewModelScope.launch {
            try {
                val response = getWeatherUseCase.execute(lat, lon, apiKey)
                _weatherData.emit(response)

            } catch (e: Exception) {
                _errorMessage.emit("Failure: ${e.message}")
            }
        }



    fun fetchWeatherHistory() {
        viewModelScope.launch {
            try {
                val history = getWeatherHistoryUseCase.execute()
                _weatherHistory.emit(history)
                _errorMessage.emit(null)
            } catch (e: Exception) {
                _errorMessage.emit("Failed to fetch weather history: ${e.message}")
            }
        }
    }




}