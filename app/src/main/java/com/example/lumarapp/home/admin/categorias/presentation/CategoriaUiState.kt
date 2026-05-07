package com.example.lumarapp.home.admin.categorias.presentation

import com.example.lumarapp.home.admin.categorias.domain.model.Categoria

data class CategoriaUiState(
    val categorias: List<Categoria> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showBottomSheet: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val selectedCategoria: Categoria? = null,
    val categoriaNombre: String = "",
    val categoriaNameError: String? = null,
    val isEditing: Boolean = false,
    val isSaving: Boolean = false
) {
    val totalCategorias: Int get() = categorias.size
    val totalProductos: Int get() = categorias.sumOf { it.totalProductos }
}
