package com.example.lumarapp.home.admin.categorias.domain.usecase

import com.example.lumarapp.home.admin.categorias.domain.model.Categoria
import com.example.lumarapp.home.admin.categorias.domain.repository.CategoriaRepository
import javax.inject.Inject

class CreateCategoriaUseCase @Inject constructor(
    private val categoriaRepository: CategoriaRepository
) {
    suspend operator fun invoke(nombre: String): Result<Categoria> {
        return categoriaRepository.createCategoria(nombre)
    }
}
