package com.example.lumarapp.home.admin.presentacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lumarapp.auth.data.local.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminHomeViewModel @Inject constructor(

    private val tokenManager: TokenManager
): ViewModel() {

    private val _uiState = MutableStateFlow(AdminHomeUiState())
    val uiState: StateFlow<AdminHomeUiState> = _uiState.asStateFlow()

init {
    datosTopBar()
}

    fun datosTopBar() {
        viewModelScope.launch {

            val nombre = tokenManager.getName() ?: ""
            val rol = tokenManager.getRol() ?: 1

            val rolTexto = when (rol) {
                1 -> "Administrador"
                2 -> "Empleado"
                else -> "Desconocido"
            }

            _uiState.update {
                it.copy(
                    nombre = nombre,
                    rol = rol,
                    rolTexto = rolTexto
                )
            }
        }
    }
}