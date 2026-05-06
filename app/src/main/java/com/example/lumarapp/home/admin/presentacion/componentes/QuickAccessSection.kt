package com.example.lumarapp.home.admin.presentacion.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lumarapp.core.ui.theme.Blues
import com.example.lumarapp.core.ui.theme.Gray100
import com.example.lumarapp.core.ui.theme.Gray300
import com.example.lumarapp.core.ui.theme.Gray800

@Composable
fun QuickAccessSection() {
    Text(
        text = "Accesos rápidos",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Gray800,
        letterSpacing = (-0.1).sp
    )
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        QuickAccessItem(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.Add,
            label = "Nueva\nventa",
            isPrimary = true
        )
        QuickAccessItem(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.ShoppingCart,
            label = "Stock",
            isPrimary = false
        )
        QuickAccessItem(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.Person,
            label = "Clientes",
            isPrimary = false
        )
        QuickAccessItem(
            modifier = Modifier.weight(1f),
            icon = Icons.AutoMirrored.Filled.ArrowForward,
            label = "Productos",
            isPrimary = false
        )
    }
}

@Composable
private fun QuickAccessItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    isPrimary: Boolean
) {
    val bgColor = if (isPrimary) Blues else Gray100
    val contentColor = if (isPrimary) Color.White else Gray800
    val borderMod = if (isPrimary) Modifier else Modifier.border(1.dp, Gray300, RoundedCornerShape(14.dp))

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .then(borderMod)
            .background(bgColor)
            .padding(vertical = 12.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = label,
            fontSize = 10.5.sp,
            fontWeight = FontWeight.SemiBold,
            color = contentColor,
            lineHeight = 12.sp,
            maxLines = 2,
            textAlign = TextAlign.Center
        )
    }
}

