package com.example.domain.usecase

import com.example.domain.model.Flowers
import com.example.domain.repository.FlowerRepository


class AddFlowerUseCase(private val flowerRepository: FlowerRepository) {
    suspend operator fun invoke(flowers: Flowers) {
        flowerRepository.addFlower(flowers)
    }
}
