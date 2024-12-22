package com.example.domain

import com.example.domain.model.DomainWeatherResponse

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: DomainWeatherResponse) : WeatherState()
    data class Error(val errorMessage: String) : WeatherState()
}