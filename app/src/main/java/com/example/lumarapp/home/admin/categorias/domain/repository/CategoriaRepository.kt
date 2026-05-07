package com.example.lumarapp.home.admin.categorias.domain.repository

import com.example.lumarapp.home.admin.categorias.domain.model.Categoria

interface CategoriaRepository {

    suspend fun getCategorias(): Result<List<Categoria>>

    suspend fun createCategoria(nombre: String): Result<Categoria>

    suspend fun updateCategoria(categoriaId: String, nuevoNombre: String): Result<Categoria>

    suspend fun deleteCategoria(categoriaId: String): Result<Unit>
}
