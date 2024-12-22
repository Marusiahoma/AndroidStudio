package com.example.domain.Domains.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository


class Login(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return userRepository.login(email, password)
    }
}