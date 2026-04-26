package com.example.lumarapp.auth.presentation.screens.login

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.lumarapp.auth.presentation.screens.login.components.LoginContent


@Composable
fun LoginScreen(){


    Scaffold() {
        LoginContent(it)
    }
}






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {

    LoginScreen()
}
