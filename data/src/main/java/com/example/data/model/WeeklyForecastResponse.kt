package com.example.data.model

data class WeeklyForecastResponse(
    val daily: List<DailyWeather>
)

data class DailyWeather(
    val date: Long, // Unix timestamp
    val temp: Temp,
    val weather: List<Weather>
)

data class Temp(val day: Double)

