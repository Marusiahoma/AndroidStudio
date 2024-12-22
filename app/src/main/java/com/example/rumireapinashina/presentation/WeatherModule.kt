package com.example.rumireapinashina.presentation

import com.example.data.WeatherApiClient
import com.example.data.repository.WeatherRepositoryImpl
import com.example.domain.repository.WeatherRepository
import com.example.domain.usecase.GetWeather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    fun provideWeatherRepository(weatherApiClient: WeatherApiClient): WeatherRepository {
        return WeatherRepositoryImpl(weatherApiClient)
    }

    @Provides
    fun provideWeatherApiClient(): WeatherApiClient {
        return WeatherApiClient
    }

    @Provides
    fun provideGetWeather(weatherRepository: WeatherRepository): GetWeather {
        return GetWeather(weatherRepository)
    }
}