package com.example.lumarapp.home.admin.presentacion.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lumarapp.core.ui.theme.Gray100
import com.example.lumarapp.core.ui.theme.Gray500
import com.example.lumarapp.core.ui.theme.Gray800
import com.example.lumarapp.core.ui.theme.RedText

@Composable
fun StatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatCard(
            modifier = Modifier.weight(1f),
            label = "Esta semana",
            value = "$842K",
            delta = "+8.1%",
            deltaPositive = true
        )
        StatCard(
            modifier = Modifier.weight(1f),
            label = "Stock bajo",
            value = "14",
            delta = "3 nuevos",
            deltaPositive = false
        )
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    delta: String,
    deltaPositive: Boolean
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Gray100),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = label,
                fontSize = 11.sp,
                color = Gray500,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Gray800,
                letterSpacing = (-0.5).sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = delta,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Bold,
                color = if (deltaPositive) Color(0xFF22C55E) else RedText
            )
        }
    }
}

