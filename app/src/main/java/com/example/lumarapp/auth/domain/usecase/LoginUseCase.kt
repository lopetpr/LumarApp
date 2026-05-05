package com.example.lumarapp.auth.domain.usecase

import com.example.lumarapp.auth.domain.model.User
import com.example.lumarapp.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(user_name: String, password: String): Result<User> {
        return authRepository.login(user_name, password)
    }
}
