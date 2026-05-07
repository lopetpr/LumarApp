package com.example.lumarapp.home.admin.presentacion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.lumarapp.core.ui.components.AdminDrawer
import com.example.lumarapp.core.ui.components.TopBar
import com.example.lumarapp.home.admin.presentation.navigation.screen.AdminScreen
import com.example.lumarapp.home.admin.presentacion.componentes.AdminHomeContent
import com.example.lumarapp.home.admin.presentacion.componentes.PlaceholderAdminScreen
import kotlinx.coroutines.launch

private fun topBarInfo(route: String?, nombre: String, rolTexto: String): Pair<String, String> =
    when (route) {
        AdminScreen.Dashboard.route  -> "Bienvenido, $nombre" to "Rol: $rolTexto"
        AdminScreen.Tiendas.route    -> "Tiendas" to "Gestiona tus tiendas"
        AdminScreen.Categorias.route -> "Categorías" to "Gestiona tus categorías"
        AdminScreen.Productos.route  -> "Productos" to "Gestiona tus productos"
        AdminScreen.Inventario.route -> "Inventario" to "Control de stock"
        AdminScreen.Clientes.route   -> "Clientes" to "Gestiona tus clientes"
        AdminScreen.Ventas.route     -> "Ventas" to "Historial de ventas"
        AdminScreen.NuevaVenta.route -> "Nueva Venta" to "Registrar una venta"
        AdminScreen.Empleados.route  -> "Empleados" to "Gestiona tu equipo"
        else                         -> "Lumar" to ""
    }

@Composable
fun AdminHomeScreen(
    onLogout: () -> Unit,
    vm: AdminHomeViewModel = hiltViewModel()
) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val innerNavController = rememberNavController()
    val backStackEntry by innerNavController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: AdminScreen.Dashboard.route

    val (titulo, detalles) = topBarInfo(currentRoute, state.nombre, state.rolTexto)
    val showBottomBar = currentRoute != AdminScreen.NuevaVenta.route

    LaunchedEffect(state.logoutTriggered) {
        if (state.logoutTriggered) {
            vm.onLogoutHandled()
            onLogout()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AdminDrawer(
                currentRoute = currentRoute,
                userName = state.nombre,
                userInitials = state.nombre.take(2).uppercase(),
                userEmail = state.email,
                onItemClick = { route ->
                    scope.launch { drawerState.close() }
                    innerNavController.navigate(route) {
                        popUpTo(AdminScreen.Dashboard.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onLogoutClick = {
                    scope.launch { drawerState.close() }
                    vm.onLogout()
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    titulo = titulo,
                    detalles = detalles,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    navController = innerNavController,
                    startDestination = AdminScreen.Dashboard.route
                ) {
                    composable(AdminScreen.Dashboard.route) {
                        AdminHomeContent(paddingValues = paddingValues)
                    }
                    composable(AdminScreen.Tiendas.route) {
                        PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Tiendas")
                    }
                    composable(AdminScreen.Categorias.route) {
                        PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Categorías")
                    }
                    composable(AdminScreen.Productos.route) {
                        PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Productos")
                    }
                    composable(AdminScreen.Inventario.route) {
                        PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Inventario")
                    }
                    composable(AdminScreen.Clientes.route) {
                        PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Clientes")
                    }
                    composable(AdminScreen.Ventas.route) {
                        PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Ventas")
                    }
                    composable(AdminScreen.NuevaVenta.route) {
                        PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Nueva Venta")
                    }
                    composable(AdminScreen.Empleados.route) {
                        PlaceholderAdminScreen(paddingValues = paddingValues, titulo = "Empleados")
                    }
                }

                AnimatedVisibility(
                    visible = showBottomBar,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }
                ) {
                    AdminBottomBar(
                        currentRoute = currentRoute,
                        onItemClick = { route ->
                            innerNavController.navigate(route) {
                                popUpTo(AdminScreen.Dashboard.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onAddClick = {
                            innerNavController.navigate(AdminScreen.NuevaVenta.route) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    }
}
