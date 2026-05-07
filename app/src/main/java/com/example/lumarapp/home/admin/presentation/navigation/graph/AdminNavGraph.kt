package com.example.lumarapp.home.admin.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lumarapp.core.navigation.Graph
import com.example.lumarapp.home.admin.presentation.navigation.screen.AdminScreen
import com.example.lumarapp.home.admin.presentacion.AdminHomeScreen

fun NavGraphBuilder.adminNavGraph(navController: NavHostController) {
    navigation(
        startDestination = AdminScreen.Home.route,
        route = Graph.ADMIN
    ) {
        composable(AdminScreen.Home.route) {
            AdminHomeScreen(
                onLogout = {
                    navController.navigate(Graph.AUTH) {
                        popUpTo(Graph.ROOT) { inclusive = true }
                    }
                }
            )
        }
    }
}
