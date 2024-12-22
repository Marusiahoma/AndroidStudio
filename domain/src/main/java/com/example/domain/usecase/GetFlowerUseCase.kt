package com.example.domain.usecase

import com.example.domain.model.Flowers
import com.example.domain.repository.FlowerRepository


class GetFlowerUseCase(private val impressionRepository: FlowerRepository) {
    suspend operator fun invoke(): List<Flowers> {
        return impressionRepository.getFlowers()
    }
}
