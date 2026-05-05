package com.example.lumarapp.auth.domain.repository

import com.example.lumarapp.auth.domain.model.User

interface AuthRepository {
    suspend fun login(user_name: String, password: String): Result<User>
    suspend fun logout()
    suspend fun isLoggedIn(): Boolean
    suspend fun getCurrentToken(): String?
}
