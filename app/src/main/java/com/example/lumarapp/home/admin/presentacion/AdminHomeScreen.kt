package com.example.lumarapp.home.admin.presentacion

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lumarapp.core.ui.components.TopBar
import com.example.lumarapp.home.admin.presentacion.componentes.AdminHomeContent

@Composable
fun AdminHomeScreen(
    vm: AdminHomeViewModel = hiltViewModel()
) {
    val state by vm.uiState.collectAsStateWithLifecycle()

    val statetitulo = "Bienvenido, ${state.nombre}"
    val detalles = "Rol: ${state.rolTexto}"


    Scaffold(
        topBar = { TopBar(
            titulo = statetitulo,
            detalles = detalles

        ) }
    ) { paddingValues ->
        AdminHomeContent(paddingValues)
    }
}
