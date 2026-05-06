package com.example.lumarapp.home.admin.cateforias.data.remote

import com.example.lumarapp.home.admin.cateforias.domain.model.Categoria
import com.google.gson.annotations.SerializedName

data class CategoriaResponse(


    @SerializedName("id")
    val categoriaId: String,
    @SerializedName("categoria")
    val categoria: String,






//    "id": "af18c8d3-b31a-41e3-9736-ba5553d46015",
//"categoria": "dulce",
//"createdAt": "2026-04-14T04:03:59.738Z",
//"updateAt": "2026-04-14T04:10:53.382Z"
)