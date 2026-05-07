package com.example.lumarapp.home.admin.categorias.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lumarapp.home.admin.categorias.domain.model.Categoria
import com.example.lumarapp.home.admin.categorias.domain.usecase.CreateCategoriaUseCase
import com.example.lumarapp.home.admin.categorias.domain.usecase.DeleteCategoriaUseCase
import com.example.lumarapp.home.admin.categorias.domain.usecase.GetCategoriasUseCase
import com.example.lumarapp.home.admin.categorias.domain.usecase.UpdateCategoriaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriaViewModel @Inject constructor(
    private val getCategoriasUseCase: GetCategoriasUseCase,
    private val createCategoriaUseCase: CreateCategoriaUseCase,
    private val updateCategoriaUseCase: UpdateCategoriaUseCase,
    private val deleteCategoriaUseCase: DeleteCategoriaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriaUiState())
    val uiState: StateFlow<CategoriaUiState> = _uiState.asStateFlow()

    init {
        loadCategorias()
    }

    fun loadCategorias() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getCategoriasUseCase()
                .onSuccess { categorias ->
                    _uiState.update { it.copy(isLoading = false, categorias = categorias) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
        }
    }

    fun onShowCreateSheet() {
        _uiState.update {
            it.copy(
                showBottomSheet = true,
                isEditing = false,
                selectedCategoria = null,
                categoriaNombre = "",
                categoriaNameError = null
            )
        }
    }

    fun onShowEditSheet(categoria: Categoria) {
        _uiState.update {
            it.copy(
                showBottomSheet = true,
                isEditing = true,
                selectedCategoria = categoria,
                categoriaNombre = categoria.categoria,
                categoriaNameError = null
            )
        }
    }

    fun onDismissSheet() {
        _uiState.update {
            it.copy(showBottomSheet = false, selectedCategoria = null, categoriaNombre = "", categoriaNameError = null)
        }
    }

    fun onCategoriaNombreChange(nombre: String) {
        _uiState.update { it.copy(categoriaNombre = nombre, categoriaNameError = null) }
    }

    fun onSaveCategoria() {
        val state = _uiState.value
        val nombre = state.categoriaNombre.trim()

        if (nombre.isBlank()) {
            _uiState.update { it.copy(categoriaNameError = "El nombre es obligatorio") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }

            val result = if (state.isEditing && state.selectedCategoria != null) {
                updateCategoriaUseCase(state.selectedCategoria.id, nombre)
            } else {
                createCategoriaUseCase(nombre)
            }

            result
                .onSuccess {
                    _uiState.update { it.copy(isSaving = false, showBottomSheet = false, categoriaNombre = "") }
                    loadCategorias()
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isSaving = false, errorMessage = error.message) }
                }
        }
    }

    fun onShowDeleteDialog(categoria: Categoria) {
        _uiState.update { it.copy(showDeleteDialog = true, selectedCategoria = categoria) }
    }

    fun onDismissDeleteDialog() {
        _uiState.update { it.copy(showDeleteDialog = false, selectedCategoria = null) }
    }

    fun onConfirmDelete() {
        val categoria = _uiState.value.selectedCategoria ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(showDeleteDialog = false, isLoading = true) }
            deleteCategoriaUseCase(categoria.id)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false, selectedCategoria = null) }
                    loadCategorias()
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error.message, selectedCategoria = null) }
                }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}
