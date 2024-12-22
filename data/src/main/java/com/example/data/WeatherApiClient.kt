package com.example.data


import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.data.Service.WeatherApiService
import com.example.domain.model.DomainWeatherResponse
import retrofit2.Response

object WeatherApiClient {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val apiService: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }

    suspend fun getWeather(latitude: Double, longitude: Double, apiKey: String): DomainWeatherResponse? {
        Log.d("WeatherApiClient", "Fetching weather for lat: $latitude, lon: $longitude")

        val response: Response<DomainWeatherResponse> = apiService.getWeather(latitude, longitude, apiKey)

        if (response.isSuccessful) {
            return response.body()
        } else {
            Log.e("WeatherApiClient", "Error fetching weather: ${response.code()} - ${response.message()}")
            return null
        }
    }
}