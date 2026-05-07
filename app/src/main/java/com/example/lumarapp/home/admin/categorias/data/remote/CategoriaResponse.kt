package com.example.lumarapp.home.admin.categorias.data.remote

import com.google.gson.annotations.SerializedName

data class CategoriaResponse(
    @SerializedName("id")
    val categoriaId: String,
    @SerializedName("categoria")
    val categoria: String,
    @SerializedName("totalProductos")
    val totalProductos: Int = 0
)
