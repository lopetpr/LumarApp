package com.example.lumarapp.home.admin.cateforias.data.repository

import com.example.lumarapp.home.admin.cateforias.data.remote.CategoriaService
import com.example.lumarapp.home.admin.cateforias.domain.model.Categoria
import com.example.lumarapp.home.admin.cateforias.domain.repository.CategoriaRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriaRepositoryImpl @Inject constructor(
    private val categoriaService: CategoriaService


) : CategoriaRepository {
    override suspend fun getCategorias(): Result<List<Categoria>> {
        return try {
            val response = categoriaService.getCategorias()
            if (response.isSuccessful) {
                val categorias = response.body().orEmpty().map {
                    Categoria(
                        id = it.categoriaId,
                        categoria = it.categoria
                    )
                }
                Result.success(categorias)
            } else {
                Result.failure(IllegalStateException("Error ${response.code()} al obtener categorias"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun addCategoria(categoria: Categoria): Result<Categoria> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCategoria(categoria: Categoria): Result<Categoria> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCategoria(
        oldCategoria: Categoria,
        newCategoria: Categoria
    ): Result<Categoria> {
        TODO("Not yet implemented")
    }

}