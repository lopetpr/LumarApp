package com.example.lumarapp.home.admin.presentacion.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lumarapp.core.ui.theme.Blues700
import com.example.lumarapp.core.ui.theme.BluesDark
import com.example.lumarapp.core.ui.theme.GreenBg
import com.example.lumarapp.core.ui.theme.GreenSoft

@Composable
fun HeroSalesCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(BluesDark)
            .padding(18.dp)
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .offset(x = 60.dp, y = (-30).dp)
                .align(Alignment.TopEnd)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Blues700.copy(alpha = 0.55f), Color.Transparent)
                    ),
                    shape = CircleShape
                )
        )

        Column {
            Text(
                text = "VENTAS HOY · TODAS LAS TIENDAS",
                color = Color.White.copy(alpha = 0.65f),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.6.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$128,450",
                color = Color.White,
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-1.5).sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(
                    modifier = Modifier
                        .background(GreenBg, RoundedCornerShape(999.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        tint = GreenSoft,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "+12.4%",
                        color = GreenSoft,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "vs. ayer · 47 transacciones",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            MiniChart()
        }
    }
}

@Composable
private fun MiniChart() {
    val bars = listOf(35, 50, 42, 65, 48, 72, 60, 88, 70, 92, 78, 100)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        bars.forEachIndexed { index, height ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((height * 38 / 100).dp)
                        .align(Alignment.BottomCenter)
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            if (index >= 9) Color.White
                            else Color.White.copy(alpha = 0.3f)
                        )
                )
            }
        }
    }
}

