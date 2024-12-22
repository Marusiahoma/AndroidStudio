package com.example.domain.repository

import com.example.domain.model.Flowers


interface FlowerRepository {
    suspend fun addFlower(flowers: Flowers)
    suspend fun getFlowers(): List<Flowers>
}
