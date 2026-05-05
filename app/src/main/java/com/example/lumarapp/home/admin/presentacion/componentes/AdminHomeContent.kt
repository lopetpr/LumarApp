package com.example.lumarapp.home.admin.presentacion.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lumarapp.core.ui.theme.BluesDark
import com.example.lumarapp.core.ui.theme.Blues
import com.example.lumarapp.core.ui.theme.Blues700
import com.example.lumarapp.core.ui.theme.Gray100
import com.example.lumarapp.core.ui.theme.Gray300
import com.example.lumarapp.core.ui.theme.Gray500
import com.example.lumarapp.core.ui.theme.Gray800
import com.example.lumarapp.core.ui.theme.GreenBg
import com.example.lumarapp.core.ui.theme.GreenSoft
import com.example.lumarapp.core.ui.theme.LumarAppTheme
import com.example.lumarapp.core.ui.theme.RedSoft
import com.example.lumarapp.core.ui.theme.RedText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminHomeContent(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        item { HeroSalesCard() }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { StatsRow() }
        item { Spacer(modifier = Modifier.height(18.dp)) }
        item { QuickAccessSection() }
        item { Spacer(modifier = Modifier.height(22.dp)) }
        item { RecentSalesSection() }
        item { Spacer(modifier = Modifier.height(22.dp)) }
        item { LowStockSection() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun HeroSalesCard() {
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

@Composable
private fun StatsRow() {
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

@Composable
private fun QuickAccessSection() {
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
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
private fun RecentSalesSection() {
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
        SaleItem("Cliente s/n", "L. Centro", "$640", "Mariana F.", "1 h"),
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

@Composable
private fun LowStockSection() {
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
        StockItem("Brisa de Cedro EDT 50ml", "L. Centro", 4),
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
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
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



@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdminHomeContentPreview() {
    LumarAppTheme {
        Scaffold(
            topBar = { AdminHomeTopBar() }
        ) { padding ->
            AdminHomeContent(padding)
        }
    }
}
