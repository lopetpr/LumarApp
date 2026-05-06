package com.example.lumarapp.home.admin.presentacion.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lumarapp.core.ui.theme.LumarAppTheme

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


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun AdminHomeContentPreview() {
//    LumarAppTheme {
//        Scaffold(
//            topBar = { AdminHomeTopBar() }
//        ) { padding ->
//            AdminHomeContent(padding)
//        }
//    }
//}
