package com.example.lumarapp.auth.presentation.navigation.screen



sealed class AuthScreen(val route: String){


    object Login : AuthScreen("login")

}
