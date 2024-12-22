package com.example.data.repository

import com.example.data.WeatherApiClient
import com.example.domain.model.DomainWeatherResponse
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiClient: WeatherApiClient
) : WeatherRepository {

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double, apiKey: String): DomainWeatherResponse {
        return weatherApiClient.getWeather(latitude, longitude, apiKey)
            ?: throw Exception("Error fetching weather data")
    }
}
