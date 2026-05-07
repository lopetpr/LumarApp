package com.example.lumarapp.home.admin.presentation.navigation.screen

sealed class AdminScreen(val route: String) {
    object Home      : AdminScreen("home")
    object Dashboard : AdminScreen("dashboard")
    object Tiendas   : AdminScreen("tiendas")
    object Categorias: AdminScreen("categorias")
    object Productos : AdminScreen("productos")
    object Inventario: AdminScreen("inventario")
    object Clientes  : AdminScreen("clientes")
    object Ventas    : AdminScreen("ventas")
    object NuevaVenta: AdminScreen("nueva_venta")
    object Empleados : AdminScreen("empleados")
}
