package com.example.lumarapp.home.admin.categorias.domain.usecase

import com.example.lumarapp.home.admin.categorias.domain.repository.CategoriaRepository
import javax.inject.Inject

class DeleteCategoriaUseCase @Inject constructor(
    private val categoriaRepository: CategoriaRepository
) {
    suspend operator fun invoke(categoriaId: String): Result<Unit> {
        return categoriaRepository.deleteCategoria(categoriaId)
    }
}
