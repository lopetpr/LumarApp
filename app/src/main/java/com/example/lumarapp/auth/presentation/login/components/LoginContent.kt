package com.example.lumarapp.auth.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lumarapp.R
import com.example.lumarapp.auth.presentation.login.LoginUiState
import com.example.lumarapp.core.ui.components.DefaultTextField
import com.example.lumarapp.core.ui.theme.Blues
import com.example.lumarapp.core.ui.theme.Gray300
import com.example.lumarapp.core.ui.theme.Gray500
import com.example.lumarapp.core.ui.theme.Gray800
import com.example.lumarapp.core.ui.theme.LumarAppTheme

@Composable
fun LoginContent(
    paddingValues: PaddingValues,
    state: LoginUiState,
    onUserChange: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp)
                .padding(top = 100.dp, bottom = 55.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoSection()

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FormSection(
                    state = state,
                    passwordVisible = passwordVisible,
                    onPasswordVisibleToggle = { passwordVisible = !passwordVisible },
                    onUserChange = onUserChange,
                    onPasswordChanged = onPasswordChanged,
                    onLoginClick = onLoginClick
                )

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "v2.4.1 · Lumar Perfumería",
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Medium,
                    color = Gray500,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun LogoSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.loginlumar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(top = 5.dp)
                .size(150.dp)
                .clip(CircleShape)
                .border(6.dp, Color.White, CircleShape)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "LUMAR",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Gray800,
            letterSpacing = (-1.1).sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Sistema de gestión interna",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Gray500
        )
    }
}

@Composable
private fun FormSection(
    state: LoginUiState,
    passwordVisible: Boolean,
    onPasswordVisibleToggle: () -> Unit,
    onUserChange: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    val errorColor = Color.Red

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        DefaultTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.user,
            onValueChange = onUserChange,
            label = "Usuario",
            icon = Icons.Default.Person,
            keyboardType = KeyboardType.Text,
            isError = state.userError != null,
            placeholder = "m.fernandez"
        )
        state.userError?.let {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color.Red,
                        shape = RoundedCornerShape(8.dp) // puedes cambiar a 0.dp si lo quieres cuadrado
                    )
                    .padding(10.dp) // espacio interno
            ) {
                Text(
                    text = it,
                    fontSize = 11.sp,
                    color = Color.White
                )
            }

        }

        DefaultTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            onValueChange = onPasswordChanged,
            label = "Contraseña",
            icon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,
            isError = state.passwordError != null,
            placeholder = "••••••••",
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onPasswordVisibleToggle) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = null
                    )
                }
            }
        )
        state.passwordError?.let {
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color.Red,
                            shape = RoundedCornerShape(8.dp) // puedes cambiar a 0.dp si lo quieres cuadrado
                        )
                        .padding(10.dp) // espacio interno
                ) {
                    Text(
                        text = it,
                        fontSize = 11.sp,
                        color = Color.White
                    )
                }


        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Text(
                text = "¿Olvidaste tu contraseña?",
                fontSize = 12.5.sp,
                fontWeight = FontWeight.SemiBold,
                color = Gray500
            )
        }

        state.errorMessage?.let {
            Text(it, fontSize = 12.sp, color = errorColor)
        }

        Button(
            onClick = onLoginClick,
            enabled = !state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blues,
                contentColor = Color.White,
                disabledContainerColor = Gray300,
                disabledContentColor = Gray500
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = if (state.isLoading) "Cargando..." else "Iniciar sesión",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.2).sp
            )
            if (!state.isLoading) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginContentPreview() {
    LumarAppTheme {
        LoginContent(
            paddingValues = PaddingValues(0.dp),
            state = LoginUiState(),
            onUserChange = {},
            onPasswordChanged = {},
            onLoginClick = {}
        )
    }
}
