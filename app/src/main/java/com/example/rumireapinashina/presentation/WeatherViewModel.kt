package com.example.rumireapinashina.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.WeatherState
import com.example.domain.usecase.GetWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeather: GetWeather
) : ViewModel() {

    private val _weatherState = MediatorLiveData<WeatherState>()
    val weatherState: LiveData<WeatherState> get() = _weatherState

    fun fetchWeather(latitude: Double, longitude: Double, apiKey: String) {
        _weatherState.value = WeatherState.Loading

        viewModelScope.launch {
            try {
                val weather = getWeather.execute(latitude, longitude, apiKey)
                _weatherState.value = WeatherState.Success(weather)
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(e.message ?: "Unknown error")
            }
        }
    }
}