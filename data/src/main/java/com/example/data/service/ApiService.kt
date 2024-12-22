package com.example.data.Service


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.data.model.CurrentWeatherResponse

interface ApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): CurrentWeatherResponse
}


