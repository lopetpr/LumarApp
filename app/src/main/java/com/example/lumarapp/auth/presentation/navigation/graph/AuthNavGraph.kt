package com.example.lumarapp.auth.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lumarapp.auth.presentation.navigation.screen.AuthScreen
import com.example.lumarapp.auth.presentation.screens.login.LoginScreen
import com.example.lumarapp.presentation.navigation.Graph


fun NavGraphBuilder.AuthNavGraph(navController: NavHostController){

    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route

    ){

        composable (route = AuthScreen.Login.route ){

            LoginScreen()


        }
    }
}