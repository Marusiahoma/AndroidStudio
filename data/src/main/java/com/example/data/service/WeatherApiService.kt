package com.example.data.Service

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.data.model.CurrentWeatherResponse
import com.example.data.model.WeeklyForecastResponse
import com.example.domain.model.DomainWeatherResponse
import retrofit2.Response

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): Response<DomainWeatherResponse>
}
