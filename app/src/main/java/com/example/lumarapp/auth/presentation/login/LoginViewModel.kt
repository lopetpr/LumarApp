package com.example.lumarapp.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lumarapp.auth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUserChange(user: String) {
        _uiState.update { it.copy(user = user, userError = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, passwordError = null) }
    }

    fun onLoginClick() {
        val currentState = _uiState.value
        if (!validateInputs(currentState)) return

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            loginUseCase(currentState.user.trim(), currentState.password)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false, isLoginSuccessful = true) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = error.message ?: "Error desconocido")
                    }
                }
        }
    }

    private fun validateInputs(state: LoginUiState): Boolean {
        var isValid = true

        val userError = when {
            state.user.isBlank() -> { isValid = false; "El usuario es obligatorio" }
            else -> null
        }

        val passwordError = when {
            state.password.isBlank() -> { isValid = false; "La contraseña es obligatoria" }
            state.password.length < 8 -> { isValid = false; "La contraseña debe tener al menos 8 caracteres" }
            else -> null
        }

        if (!isValid) {
            _uiState.update { it.copy(userError = userError, passwordError = passwordError) }
        }

        return isValid
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}
