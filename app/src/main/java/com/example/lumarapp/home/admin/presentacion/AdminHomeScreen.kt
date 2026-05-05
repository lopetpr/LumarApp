package com.example.lumarapp.home.admin.presentacion

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.lumarapp.home.admin.presentacion.componentes.AdminHomeContent
import com.example.lumarapp.home.admin.presentacion.componentes.AdminHomeTopBar

@Composable
fun AdminHomeScreen() {
    Scaffold(
        topBar = { AdminHomeTopBar() }
    ) { paddingValues ->
        AdminHomeContent(paddingValues)
    }
}
