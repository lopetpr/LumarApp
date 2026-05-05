package com.example.lumarapp.splash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lumarapp.auth.domain.usecase.CheckSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class StartDestination {
    object Loading : StartDestination()
    object Login : StartDestination()
    object Home : StartDestination()
}


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkSessionUseCase: CheckSessionUseCase
) : ViewModel() {

    private val _destination = MutableStateFlow<StartDestination>(StartDestination.Loading)
    val destination: StateFlow<StartDestination> = _destination.asStateFlow()

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            val minDelay = async { delay(1000L) }
            val isLoggedIn = checkSessionUseCase()
            minDelay.await()
            _destination.value = if (isLoggedIn) StartDestination.Home else StartDestination.Login
        }
    }
}
