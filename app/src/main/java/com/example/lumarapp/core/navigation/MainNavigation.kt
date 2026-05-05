package com.example.lumarapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.lumarapp.auth.presentation.login.LoginScreen
import com.example.lumarapp.home.admin.presentacion.AdminHomeScreen

import com.example.lumarapp.splash.ui.SplashScreen


@Composable
fun MainNavigation(

    navController : NavHostController
){


    NavHost(
        navController = navController,
        startDestination = "splash",

    ){

        composable("splash") {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        // popUpTo elimina el splash del back stack
                        // para que el usuario no pueda volver a él con "atrás"
                        popUpTo("splash") { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }


        composable("login"){


            LoginScreen(
                onLoginSuccess = {


                    navController.navigate("home"){


                        popUpTo("login") {inclusive= true}
                    }
                }
            )
        }

        composable("home") {


            AdminHomeScreen()



//            HomeScreen(
////                onLogout = {
////                    navController.navigate("login") {
////                        // Al cerrar sesión, limpiamos TODO el back stack.
////                        // popUpTo(0) elimina todas las pantallas desde la raíz.
////                        popUpTo(0) { inclusive = true }
////                    }
////                }
//            )
        }


    }
}