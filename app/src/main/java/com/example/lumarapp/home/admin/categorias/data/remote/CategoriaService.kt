package com.example.lumarapp.home.admin.categorias.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CategoriaService {

    @POST("categorias")
    suspend fun create(@Body request: CategoriaRequest): Response<CategoriaResponse>

    @GET("categorias")
    suspend fun getCategorias(): Response<List<CategoriaResponse>>

    @GET("categorias/{categoriaId}")
    suspend fun getCategoria(@Path("categoriaId") categoriaId: String): Response<CategoriaResponse>

    @PATCH("categorias/{categoriaId}")
    suspend fun updateCategoria(
        @Path("categoriaId") categoriaId: String,
        @Body request: CategoriaRequest
    ): Response<CategoriaResponse>

    @DELETE("categorias/{categoriaId}")
    suspend fun deleteCategoria(@Path("categoriaId") categoriaId: String): Response<Unit>
}
