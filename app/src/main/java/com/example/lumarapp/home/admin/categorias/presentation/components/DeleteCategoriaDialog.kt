package com.example.lumarapp.home.admin.categorias.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.lumarapp.core.ui.theme.Gray800
import com.example.lumarapp.core.ui.theme.RedText

@Composable
fun DeleteCategoriaDialog(
    categoriaNombre: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,

) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        title = {
            Text(
                text = "Eliminar categoría",
                fontWeight = FontWeight.Bold,
                color = Gray800
            )
        },
        text = {
            Text(
                text = "¿Estás seguro de eliminar \"$categoriaNombre\"? Esta acción no se puede deshacer.",
                color = Gray800
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = RedText)
            ) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = Gray800)
            }
        }
    )
}
