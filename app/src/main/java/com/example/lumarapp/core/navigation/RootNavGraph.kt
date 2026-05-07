package com.example.lumarapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.lumarapp.auth.presentation.navigation.graph.authNavGraph
import com.example.lumarapp.home.admin.presentation.navigation.graph.adminNavGraph
import com.example.lumarapp.splash.presentation.navigation.graph.splashNavGraph

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graph.SPLASH,
        route = Graph.ROOT
    ) {
        splashNavGraph(navController)
        authNavGraph(navController)
        adminNavGraph(navController)
    }
}
