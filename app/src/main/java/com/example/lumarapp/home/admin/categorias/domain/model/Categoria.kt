package com.example.lumarapp.home.admin.categorias.domain.model

data class Categoria(
    val id: String,
    val categoria: String,
    val totalProductos: Int = 0
)
