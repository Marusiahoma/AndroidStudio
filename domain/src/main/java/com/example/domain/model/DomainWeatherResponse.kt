package com.example.domain.model

data class DomainWeatherResponse(
    val main: MainWeatherData,
    val weather: List<WeatherDescription>
)

data class MainWeatherData(
    val temp: Double // Температура в Кельвинах
)

data class WeatherDescription(
    val description: String
)

