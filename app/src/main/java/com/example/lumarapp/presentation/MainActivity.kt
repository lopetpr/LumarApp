package com.example.lumarapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lumarapp.auth.presentation.screens.login.LoginScreen
import com.example.lumarapp.presentation.components.ui.theme.LumarAppTheme
import com.example.lumarapp.presentation.navigation.graph.RootNavGraph

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LumarAppTheme {

                navController = rememberNavController()
                RootNavGraph(navController= navController)

            }
        }
    }
}