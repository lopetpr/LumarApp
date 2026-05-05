package com.example.lumarapp.auth.presentation.login

data class LoginUiState(
    val user: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccessful: Boolean = false,
    val userError: String? = null,
    val passwordError: String? = null
)
