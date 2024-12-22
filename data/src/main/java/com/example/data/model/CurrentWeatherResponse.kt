package com.example.data.model

data class CurrentWeatherResponse(
    val main: Main,
    val weather: List<Weather>
)

data class Main(val temp: Double)
data class Weather(val description: String)

