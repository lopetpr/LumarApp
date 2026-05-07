package com.example.lumarapp.splash.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lumarapp.core.navigation.Graph
import com.example.lumarapp.splash.presentation.navigation.screen.SplashScreen
import com.example.lumarapp.splash.ui.SplashScreen as SplashScreenComposable

fun NavGraphBuilder.splashNavGraph(navController: NavHostController) {
    navigation(
        startDestination = SplashScreen.ROUTE,
        route = Graph.SPLASH
    ) {
        composable(SplashScreen.ROUTE) {
            SplashScreenComposable(
                onNavigateToLogin = {
                    navController.navigate(Graph.AUTH) {
                        popUpTo(Graph.SPLASH) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Graph.ADMIN) {
                        popUpTo(Graph.SPLASH) { inclusive = true }
                    }
                }
            )
        }
    }
}
