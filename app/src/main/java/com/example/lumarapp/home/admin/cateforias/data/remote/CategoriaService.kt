package com.example.lumarapp.home.admin.cateforias.data.remote


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface CategoriaService {

    @POST("categorias")
    suspend fun create(@Body request: CategoriaRequest): Response<CategoriaResponse>

    @GET("categorias")
    suspend fun getCategorias(): Response<List<CategoriaResponse>>

    @PATCH("categorias/{categoriaId}")
    suspend fun updateCategoria(oldCategoria: String, newCategoria: String): Response<CategoriaResponse>

    @DELETE("categorias")
    suspend fun deleteCategoria(categoria: String): Response<CategoriaResponse>




}