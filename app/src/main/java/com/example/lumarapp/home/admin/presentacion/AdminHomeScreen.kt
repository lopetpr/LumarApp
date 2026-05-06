package com.example.lumarapp.home.admin.presentacion

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lumarapp.home.admin.presentacion.componentes.AdminHomeContent
import com.example.lumarapp.home.admin.presentacion.componentes.AdminHomeTopBar

@Composable
fun AdminHomeScreen(
    vm: AdminHomeViewModel = hiltViewModel()
) {
    val state by vm.uiState.collectAsStateWithLifecycle()




    Scaffold(
        topBar = { AdminHomeTopBar(

            state = state
        ) }
    ) { paddingValues ->
        AdminHomeContent(paddingValues)
    }
}
