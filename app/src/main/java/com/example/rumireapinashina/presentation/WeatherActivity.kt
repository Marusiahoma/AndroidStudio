package com.example.rumireapinashina.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import androidx.lifecycle.ViewModelProvider
import com.example.domain.WeatherState
import androidx.lifecycle.MediatorLiveData
import com.example.rumireapinashina.R
import com.example.rumireapinashina.presentation.WeatherViewModel
import com.example.rumireapinashina.BaseActivity

class WeatherActivity : BaseActivity() {

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var forecastTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var weatherImageView: ImageView

    private val temperatureMediatorLiveData = MediatorLiveData<String>()

    private val apiKey = "a960c7bf7c3e3e2d4b17458fadaafec6" // API ключ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupNavigationDrawer()

        temperatureTextView = findViewById(R.id.temperatureTextView)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        temperatureMediatorLiveData.addSource(weatherViewModel.weatherState) { state ->
            when (state) {
                is WeatherState.Success -> {
                    val temperatureInCelsius = state.weather.main.temp - 273.15
                    val roundedTemperature = String.format("%.1f°C", temperatureInCelsius)
                    temperatureMediatorLiveData.value = roundedTemperature
                }
                is WeatherState.Error -> {
                    temperatureMediatorLiveData.value = "Error: ${state.errorMessage}"
                }
                else -> {
                    temperatureMediatorLiveData.value = "Loading..."
                }
            }
        }

        temperatureMediatorLiveData.observe(this) { temperature ->
            temperatureTextView.text = temperature
        }

        fetchWeatherByLocation()
    }

    private fun fetchWeatherByLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                weatherViewModel.fetchWeather(location.latitude, location.longitude, apiKey)
            } else {
                Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { e ->
            Toast.makeText(this, "Error getting location", Toast.LENGTH_SHORT).show()
        }
    }




}
