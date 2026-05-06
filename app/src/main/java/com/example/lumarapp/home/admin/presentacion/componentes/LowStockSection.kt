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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lumarapp.core.ui.theme.Blues
import com.example.lumarapp.core.ui.theme.Gray300
import com.example.lumarapp.core.ui.theme.Gray500
import com.example.lumarapp.core.ui.theme.Gray800
import com.example.lumarapp.core.ui.theme.RedSoft
import com.example.lumarapp.core.ui.theme.RedText

@Composable
fun LowStockSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "Atención · Stock bajo",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Gray800,
            letterSpacing = (-0.1).sp
        )
        Text(
            text = "Ver más",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Blues
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    val items = listOf(
        StockItem("Eau de Parfum Ámbar 100ml", "L. Norte", 2),
        StockItem("Brisa de Cedro EDT 50ml", "L. Centro", 4)
    )

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            items.forEachIndexed { index, item ->
                StockRow(item)
                if (index < items.lastIndex) {
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

private data class StockItem(
    val product: String,
    val store: String,
    val quantity: Int
)

@Composable
private fun StockRow(item: StockItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(RedSoft),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "💧",
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.product,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Gray800,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.store,
                fontSize = 11.5.sp,
                color = Gray500
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .background(RedSoft, RoundedCornerShape(999.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = "${item.quantity} ud",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = RedText
            )
        }
    }
}

