package com.example.lumarapp.home.admin.categorias.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import com.example.lumarapp.core.ui.theme.RedSoft
import com.example.lumarapp.core.ui.theme.RedText
import com.example.lumarapp.home.admin.categorias.domain.model.Categoria
import com.example.lumarapp.home.admin.categorias.presentation.CategoriaUiState

@Composable
fun CategoriaContent(
    paddingValues: PaddingValues,
    state: CategoriaUiState,
    snackbarHostState: SnackbarHostState,
    onCreateClick: () -> Unit,
    onEditClick: (Categoria) -> Unit,
    onDeleteClick: (Categoria) -> Unit,
    onRefresh: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onCreateClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blues)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Nueva categoría",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Blues)
                }
            } else if (state.categorias.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay categorías registradas",
                        color = Gray500,
                        fontSize = 14.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                ) {
                    items(state.categorias, key = { it.id }) { categoria ->
                        CategoriaItem(
                            categoria = categoria,
                            onEditClick = { onEditClick(categoria) },
                            onDeleteClick = { onDeleteClick(categoria) }
                        )
                        if (categoria != state.categorias.last()) {
                            Divider(color = Gray300, thickness = 0.5.dp)
                        }
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun CategoriaItem(
    categoria: Categoria,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Blues)
        )

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = categoria.categoria,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Gray800
            )
            Text(
                text = "${categoria.totalProductos} productos",
                fontSize = 13.sp,
                color = Gray500
            )
        }

        IconButton(
            onClick = onEditClick,
            modifier = Modifier.size(40.dp),
            colors = IconButtonDefaults.iconButtonColors(containerColor = Gray300.copy(alpha = 0.4f))
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                tint = Gray500,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.size(40.dp),
            colors = IconButtonDefaults.iconButtonColors(containerColor = RedSoft)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar",
                tint = RedText,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
