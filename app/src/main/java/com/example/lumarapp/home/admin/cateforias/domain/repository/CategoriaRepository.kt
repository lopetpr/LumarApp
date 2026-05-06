package com.example.lumarapp.home.admin.cateforias.domain.repository

import com.example.lumarapp.home.admin.cateforias.domain.model.Categoria

interface CategoriaRepository {


    suspend fun getCategorias(): Result<List<Categoria>>

    suspend fun addCategoria(categoria: Categoria): Result<Categoria>

    suspend fun deleteCategoria(categoria: Categoria): Result<Categoria>

    suspend fun updateCategoria(oldCategoria: Categoria, newCategoria: Categoria): Result<Categoria>


}