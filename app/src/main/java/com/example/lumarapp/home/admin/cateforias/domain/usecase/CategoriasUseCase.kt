package com.example.lumarapp.home.admin.cateforias.domain.usecase

import com.example.lumarapp.home.admin.cateforias.domain.model.Categoria
import com.example.lumarapp.home.admin.cateforias.domain.repository.CategoriaRepository
import javax.inject.Inject


class CategoriasUseCase @Inject constructor(

    private val categoriaRepository: CategoriaRepository
){


    suspend operator fun invoke(): Result<List<Categoria>> {
        return categoriaRepository.getCategorias()
    }


}