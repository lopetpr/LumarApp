package com.example.lumarapp.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import com.example.lumarapp.presentation.components.ui.theme.Purple40


@Composable
fun DefaultTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text


){


    OutlinedTextField(
        modifier = modifier,
        value =value,
        onValueChange = { onValueChange(it) },
        label = { Text(text =  label) },
        singleLine = true,
        leadingIcon = {

            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = Purple40
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType= keyboardType)

        )
}