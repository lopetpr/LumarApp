package com.example.lumarapp.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.lumarapp.core.ui.theme.Gray500
import com.example.lumarapp.core.ui.theme.Gray800
import com.example.lumarapp.home.admin.presentacion.AdminHomeUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(

    titulo: String,
    detalles: String,


) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = titulo,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray800
                )
                Text(
                    text = detalles,
                    fontSize = 12.sp,
                    color = Gray500
                )
            }
        },
//        actions = {
//            IconButton(onClick = { }) {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "Buscar",
//                    tint = Gray800
//                )
//            }
//            IconButton(onClick = { }) {
//                Icon(
//                    imageVector = Icons.Default.Notifications,
//                    contentDescription = "Notificaciones",
//                    tint = Gray800
//                )
//            }
//        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}