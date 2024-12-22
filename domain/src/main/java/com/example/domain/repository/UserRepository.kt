package com.example.domain.repository

import com.example.domain.model.User


interface UserRepository {
    suspend fun register(email: String, password: String): Result<User>
    suspend fun login(email: String, password: String): Result<User>
    fun logout()
    fun getCurrentUser(): User?
}
