package com.example.lumarapp.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lumarapp.core.ui.theme.Blues
import com.example.lumarapp.core.ui.theme.Gray500
import com.example.lumarapp.core.ui.theme.Gray800
import com.example.lumarapp.home.admin.presentation.navigation.screen.AdminScreen

@Composable
fun AdminDrawer(
    currentRoute: String?,
    userName: String,
    userInitials: String,
    userEmail: String,
    onItemClick: (String) -> Unit,
    onLogoutClick: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            DrawerHeader()
            HorizontalDivider()

            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                DrawerSectionLabel("PRINCIPAL")
                DrawerNavItem(
                    icon = Icons.Filled.Dashboard,
                    label = "Dashboard",
                    route = AdminScreen.Dashboard.route,
                    currentRoute = currentRoute,
                    onClick = onItemClick
                )

                DrawerSectionLabel("CATÁLOGO")
                DrawerNavItem(
                    icon = Icons.Filled.Store,
                    label = "Tiendas",
                    route = AdminScreen.Tiendas.route,
                    currentRoute = currentRoute,
                    onClick = onItemClick
                )
                DrawerNavItem(
                    icon = Icons.AutoMirrored.Filled.Label,
                    label = "Categorías",
                    route = AdminScreen.Categorias.route,
                    currentRoute = currentRoute,
                    onClick = onItemClick
                )
                DrawerNavItem(
                    icon = Icons.Filled.Inventory2,
                    label = "Productos",
                    route = AdminScreen.Productos.route,
                    currentRoute = currentRoute,
                    onClick = onItemClick
                )

                DrawerSectionLabel("OPERACIÓN")
                DrawerNavItem(
                    icon = Icons.Filled.Archive,
                    label = "Inventario",
                    route = AdminScreen.Inventario.route,
                    currentRoute = currentRoute,
                    onClick = onItemClick
                )
                DrawerNavItem(
                    icon = Icons.Filled.People,
                    label = "Clientes",
                    route = AdminScreen.Clientes.route,
                    currentRoute = currentRoute,
                    onClick = onItemClick
                )
                DrawerNavItem(
                    icon = Icons.Filled.Receipt,
                    label = "Ventas",
                    route = AdminScreen.Ventas.route,
                    currentRoute = currentRoute,
                    onClick = onItemClick
                )

                DrawerSectionLabel("ADMINISTRACIÓN")
                DrawerNavItem(
                    icon = Icons.Filled.Person,
                    label = "Empleados",
                    route = AdminScreen.Empleados.route,
                    currentRoute = currentRoute,
                    onClick = onItemClick
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            DrawerFooter(
                initials = userInitials,
                name = userName,
                email = userEmail,
                onLogout = onLogoutClick
            )
        }
    }
}

@Composable
private fun DrawerHeader() {
    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)) {
        Text(
            text = "Lumar",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Blues
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Blues, CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "ADMINISTRADOR",
                color = Blues,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun DrawerSectionLabel(text: String) {
    Text(
        text = text,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        color = Gray500,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    )
}

@Composable
private fun DrawerNavItem(
    icon: ImageVector,
    label: String,
    route: String,
    currentRoute: String?,
    onClick: (String) -> Unit
) {
    val selected = currentRoute == route
    val contentColor = if (selected) Blues else Gray800
    val bgColor = if (selected) Blues.copy(alpha = 0.08f) else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 2.dp)
            .background(bgColor, androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
            .clickable { onClick(route) }
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            modifier = Modifier.size(22.dp)
        )
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = contentColor
        )
    }
}

@Composable
private fun DrawerFooter(
    initials: String,
    name: String,
    email: String,
    onLogout: () -> Unit
) {
    HorizontalDivider()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Blues, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials.ifEmpty { "?" },
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Gray800
            )
            if (email.isNotEmpty()) {
                Text(
                    text = email,
                    fontSize = 12.sp,
                    color = Gray500
                )
            }
        }
    }
    HorizontalDivider()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onLogout)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Cerrar sesión",
            tint = Gray500,
            modifier = Modifier.size(22.dp)
        )
        Text(
            text = "Cerrar sesión",
            color = Gray500,
            fontSize = 15.sp
        )
    }
}
