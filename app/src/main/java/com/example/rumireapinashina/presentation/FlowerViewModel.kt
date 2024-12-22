package com.example.rumireapinashina.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.domain.model.Flowers
import com.example.domain.usecase.AddFlowerUseCase
import com.example.domain.usecase.GetFlowerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.data.repository.FlowerRepositoryImpl
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FlowerViewModel(application: Application) : AndroidViewModel(application) {
    private val flowerRepository = FlowerRepositoryImpl(AppDatabase.getDatabase(application).FlowerDao())
    private val addFlowerUseCase = AddFlowerUseCase(flowerRepository)
    private val getFlowerUseCase = GetFlowerUseCase(flowerRepository)

    private val _flowers = MutableLiveData<List<Flowers>>()
    val flowers: LiveData<List<Flowers>> get() = _flowers

    fun addFlower(flower: Flowers) {
        viewModelScope.launch(Dispatchers.IO) {
            addFlowerUseCase(flower)
            loadFlower()
        }
    }

    fun loadFlower() {
        viewModelScope.launch(Dispatchers.IO) {
            val flower = getFlowerUseCase()
            _flowers.postValue(flower)
        }
    }
}

