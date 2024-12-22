    package com.example.domain.usecase

    import com.example.domain.model.DomainWeatherResponse
    import com.example.domain.repository.WeatherRepository
    import javax.inject.Inject


    class GetWeather @Inject constructor(
        private val weatherRepository: WeatherRepository
    ) {
        suspend fun execute(latitude: Double, longitude: Double, apiKey: String): DomainWeatherResponse {
            return weatherRepository.getCurrentWeather(latitude, longitude, apiKey)
        }
    }
