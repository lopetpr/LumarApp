package com.example.lumarapp.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.lumarapp.auth.presentation.navigation.graph.AuthNavGraph
import com.example.lumarapp.presentation.navigation.Graph


@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH


    ){

        AuthNavGraph(navController= navController)
    }



}