package com.example.lumarapp.home.admin.categorias.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lumarapp.core.ui.components.DefaultTextField
import com.example.lumarapp.core.ui.theme.Blues
import com.example.lumarapp.core.ui.theme.Gray300
import com.example.lumarapp.core.ui.theme.Gray800

@Composable
fun CategoriaSheetContent(
    nombre: String,
    isEditing: Boolean,
    isSaving: Boolean,
    nombreError: String?,
    onNombreChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp)
    ) {
        Text(
            text = if (isEditing) "Editar categoría" else "Nueva categoría",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Gray800
        )

        Spacer(modifier = Modifier.height(20.dp))

        DefaultTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = "Nombre de categoría",
            icon = Icons.Default.Category,
            isError = nombreError != null,
            placeholder = "Ej: Eau de Parfum"
        )

        if (nombreError != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = nombreError,
                fontSize = 12.sp,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSave,
            enabled = !isSaving,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blues,
                disabledContainerColor = Gray300
            )
        ) {
            Text(
                text = if (isSaving) "Guardando..." else "Guardar",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}
