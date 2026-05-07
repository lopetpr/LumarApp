package com.example.lumarapp.home.admin.presentacion

data class AdminHomeUiState(
    val nombre: String = "",
    val rol: Int = 1,
    val rolTexto: String = "",
    val email: String = "",
    val isLoading: Boolean = false,
    val logoutTriggered: Boolean = false
)
