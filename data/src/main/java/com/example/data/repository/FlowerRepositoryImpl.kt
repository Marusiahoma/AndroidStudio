package com.example.data.repository

import com.example.data.FlowerDao
import com.example.domain.model.Flowers
import com.example.domain.repository.FlowerRepository
import com.example.data.model.FlowerEntity

class FlowerRepositoryImpl(private val flowerDao: FlowerDao) : FlowerRepository {

    override suspend fun addFlower(flowers: Flowers) {
        val FlowerEntity = FlowerEntity(
            title = flowers.title,
            description = flowers.description,
            date = flowers.date,
            rating = flowers.rating
        )
        flowerDao.insertFlower(FlowerEntity)
    }

    override suspend fun getFlowers(): List<Flowers> {
        return flowerDao.getAllFlowers().map {
            Flowers(
                id = it.id,
                title = it.title,
                description = it.description,
                date = it.date,
                rating = it.rating
            )
        }
    }
}

