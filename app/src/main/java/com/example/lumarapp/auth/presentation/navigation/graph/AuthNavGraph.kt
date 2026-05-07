package com.example.lumarapp.auth.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lumarapp.auth.presentation.login.LoginScreen
import com.example.lumarapp.auth.presentation.navigation.screen.AuthScreen
import com.example.lumarapp.core.navigation.Graph

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        startDestination = AuthScreen.Login.route,
        route = Graph.AUTH
    ) {
        composable(AuthScreen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Graph.ADMIN) {
                        popUpTo(Graph.AUTH) { inclusive = true }
                    }
                }
            )
        }
    }
}
