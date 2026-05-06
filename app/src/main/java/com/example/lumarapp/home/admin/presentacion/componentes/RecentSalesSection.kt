package com.example.lumarapp.home.admin.presentacion.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lumarapp.core.ui.theme.Blues
import com.example.lumarapp.core.ui.theme.Gray300
import com.example.lumarapp.core.ui.theme.Gray500
import com.example.lumarapp.core.ui.theme.Gray800

@Composable
fun RecentSalesSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "Últimas ventas",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Gray800,
            letterSpacing = (-0.1).sp
        )
        Text(
            text = "Ver todas",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Blues
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    val sales = listOf(
        SaleItem("Renata Acuña", "L. Centro", "$3,240", "Daniel R.", "12 min"),
        SaleItem("Joaquín Pino", "L. Norte", "$1,890", "Bruno A.", "38 min"),
        SaleItem("Cliente s/n", "L. Centro", "$640", "Mariana F.", "1 h")
    )

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            sales.forEachIndexed { index, sale ->
                SaleRow(sale)
                if (index < sales.lastIndex) {
                    HorizontalDivider(
                        color = Gray300,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 14.dp)
                    )
                }
            }
        }
    }
}

private data class SaleItem(
    val client: String,
    val store: String,
    val total: String,
    val employee: String,
    val time: String
)

@Composable
private fun SaleRow(sale: SaleItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AvatarCircle(name = sale.client, size = 36)
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = sale.client,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold,
                color = Gray800
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${sale.store} · ${sale.employee} · hace ${sale.time}",
                fontSize = 11.5.sp,
                color = Gray500
            )
        }
        Text(
            text = sale.total,
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Gray800
        )
    }
}

@Composable
private fun AvatarCircle(name: String, size: Int) {
    val initials = name.split(" ")
        .take(2)
        .mapNotNull { it.firstOrNull()?.uppercaseChar() }
        .joinToString("")

    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(Gray300),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            fontSize = (size / 3).sp,
            fontWeight = FontWeight.Bold,
            color = Gray500
        )
    }
}

