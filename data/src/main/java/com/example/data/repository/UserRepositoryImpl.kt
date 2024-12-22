package com.example.data.repository

import com.example.data.PreferencesManager
import com.example.data.Service.AuthService
import com.example.domain.repository.UserRepository
import com.example.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val authService: AuthService, private val preferencesManager: PreferencesManager) : UserRepository {

    override suspend fun register(email: String, password: String): Result<User> = withContext(Dispatchers.IO) {
        authService.register(email, password).map { firebaseUser ->
            User(firebaseUser?.uid ?: "", email)
        }
    }

    override suspend fun login(email: String, password: String): Result<User> = withContext(Dispatchers.IO) {
        authService.login(email, password).map { firebaseUser ->
            User(firebaseUser?.uid ?: "", email)
        }
    }

    override fun logout() {
        authService.logout()
        preferencesManager.setLoggedIn(false)
    }

    override fun getCurrentUser(): User? {
        val firebaseUser = authService.getCurrentUser()
        return firebaseUser?.let { User(it.uid, it.email ?: "") }
    }
}
