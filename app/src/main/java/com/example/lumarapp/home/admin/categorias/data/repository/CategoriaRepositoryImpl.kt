package com.example.lumarapp.home.admin.categorias.data.repository

import com.example.lumarapp.home.admin.categorias.data.remote.CategoriaRequest
import com.example.lumarapp.home.admin.categorias.data.remote.CategoriaService
import com.example.lumarapp.home.admin.categorias.domain.model.Categoria
import com.example.lumarapp.home.admin.categorias.domain.repository.CategoriaRepository
import java.net.SocketTimeoutException
import java.net.UnknownHostException
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
                        categoria = it.categoria,
                        totalProductos = it.totalProductos
                    )
                }
                Result.success(categorias)
            } else {
                Result.failure(Exception(mapHttpError(response.code())))
            }
        } catch (e: Exception) {
            Result.failure(Exception(mapNetworkError(e)))
        }
    }

    override suspend fun createCategoria(nombre: String): Result<Categoria> {
        return try {
            val response = categoriaService.create(CategoriaRequest(categoria = nombre))
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                Result.success(
                    Categoria(
                        id = body.categoriaId,
                        categoria = body.categoria,
                        totalProductos = body.totalProductos
                    )
                )
            } else {
                Result.failure(Exception(mapHttpError(response.code())))
            }
        } catch (e: Exception) {
            Result.failure(Exception(mapNetworkError(e)))
        }
    }

    override suspend fun updateCategoria(categoriaId: String, nuevoNombre: String): Result<Categoria> {
        return try {
            val response = categoriaService.updateCategoria(categoriaId, CategoriaRequest(categoria = nuevoNombre))
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                Result.success(
                    Categoria(
                        id = body.categoriaId,
                        categoria = body.categoria,
                        totalProductos = body.totalProductos
                    )
                )
            } else {
                Result.failure(Exception(mapHttpError(response.code())))
            }
        } catch (e: Exception) {
            Result.failure(Exception(mapNetworkError(e)))
        }
    }

    override suspend fun deleteCategoria(categoriaId: String): Result<Unit> {
        return try {
            val response = categoriaService.deleteCategoria(categoriaId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(mapHttpError(response.code())))
            }
        } catch (e: Exception) {
            Result.failure(Exception(mapNetworkError(e)))
        }
    }

    private fun mapHttpError(code: Int): String = when (code) {
        400 -> "Datos inválidos"
        401 -> "No autorizado"
        404 -> "Categoría no encontrada"
        409 -> "Ya existe una categoría con ese nombre"
        in 500..599 -> "Error en el servidor. Intenta más tarde"
        else -> "Error inesperado ($code)"
    }

    private fun mapNetworkError(e: Exception): String = when (e) {
        is UnknownHostException -> "Sin conexión a internet"
        is SocketTimeoutException -> "El servidor no responde. Intenta más tarde"
        else -> "Error de conexión: ${e.localizedMessage}"
    }
}
