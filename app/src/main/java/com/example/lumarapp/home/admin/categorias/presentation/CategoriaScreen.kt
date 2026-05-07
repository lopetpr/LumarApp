package com.example.lumarapp.home.admin.categorias.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lumarapp.home.admin.categorias.presentation.components.CategoriaContent
import com.example.lumarapp.home.admin.categorias.presentation.components.CategoriaSheetContent
import com.example.lumarapp.home.admin.categorias.presentation.components.DeleteCategoriaDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaScreen(
    paddingValues: PaddingValues,
    onSubtitleUpdate: (String) -> Unit = {},
    vm: CategoriaViewModel = hiltViewModel()
) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(state.totalCategorias, state.totalProductos) {
        onSubtitleUpdate("${state.totalCategorias} categorías · ${state.totalProductos} productos")
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            vm.clearError()
        }
    }

    CategoriaContent(
        paddingValues = paddingValues,
        state = state,
        snackbarHostState = snackbarHostState,
        onCreateClick = vm::onShowCreateSheet,
        onEditClick = vm::onShowEditSheet,
        onDeleteClick = vm::onShowDeleteDialog,
        onRefresh = vm::loadCategorias
    )

    if (state.showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = vm::onDismissSheet,
            sheetState = sheetState,
            containerColor = Color.White
        ) {
            CategoriaSheetContent(
                nombre = state.categoriaNombre,
                isEditing = state.isEditing,
                isSaving = state.isSaving,
                nombreError = state.categoriaNameError,
                onNombreChange = vm::onCategoriaNombreChange,
                onSave = vm::onSaveCategoria
            )
        }
    }

    if (state.showDeleteDialog) {
        DeleteCategoriaDialog(
            categoriaNombre = state.selectedCategoria?.categoria ?: "",
            onConfirm = vm::onConfirmDelete,
            onDismiss = vm::onDismissDeleteDialog
        )
    }
}
