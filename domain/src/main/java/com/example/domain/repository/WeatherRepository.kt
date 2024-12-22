package com.example.domain.repository

import com.example.domain.model.DomainWeatherResponse


interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double, apiKey: String): DomainWeatherResponse
}
