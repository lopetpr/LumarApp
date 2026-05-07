package com.example.lumarapp.home.admin.categorias.domain.usecase

import com.example.lumarapp.home.admin.categorias.domain.model.Categoria
import com.example.lumarapp.home.admin.categorias.domain.repository.CategoriaRepository
import javax.inject.Inject

class GetCategoriasUseCase @Inject constructor(
    private val categoriaRepository: CategoriaRepository
) {
    suspend operator fun invoke(): Result<List<Categoria>> {
        return categoriaRepository.getCategorias()
    }
}
