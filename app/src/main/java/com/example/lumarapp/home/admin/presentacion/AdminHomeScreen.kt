package com.example.lumarapp.home.admin.presentacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lumarapp.core.ui.components.AdminBottomBar
import com.example.lumarapp.core.ui.components.AdminRoutes
import com.example.lumarapp.core.ui.components.TopBar
import com.example.lumarapp.home.admin.presentacion.componentes.AdminHomeContent
import com.example.lumarapp.home.admin.presentacion.componentes.PlaceholderAdminScreen

@Composable
fun AdminHomeScreen(
    vm: AdminHomeViewModel = hiltViewModel()
) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    val statetitulo = "Bienvenido, ${state.nombre}"
    val detalles = "Rol: ${state.rolTexto}"

    val innerNavController = rememberNavController()
    val backStackEntry by innerNavController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: AdminRoutes.INICIO

    Scaffold(
        topBar = {
            TopBar(
                titulo = statetitulo,
                detalles = detalles
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = innerNavController,
                startDestination = AdminRoutes.INICIO
            ) {
                composable(AdminRoutes.INICIO) {
                    AdminHomeContent(paddingValues = paddingValues)
                }
                composable(AdminRoutes.STOCK) {
                    PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Stock")
                }
                composable(AdminRoutes.NUEVA_VENTA) {
                    PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Nueva Venta")
                }
                composable(AdminRoutes.VENTAS) {
                    PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Ventas")
                }
                composable(AdminRoutes.CLIENTES) {
                    PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Clientes")
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
            ) {
                AdminBottomBar(
                    currentRoute = currentRoute,
                    onItemClick = { route ->
                        innerNavController.navigate(route) {
                            popUpTo(AdminRoutes.INICIO) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onAddClick = {
                        innerNavController.navigate(AdminRoutes.NUEVA_VENTA) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
