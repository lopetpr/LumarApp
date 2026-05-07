package com.example.lumarapp.di

import com.example.lumarapp.auth.data.repository.AuthRepositoryImpl
import com.example.lumarapp.auth.domain.repository.AuthRepository
import com.example.lumarapp.home.admin.categorias.data.repository.CategoriaRepositoryImpl
import com.example.lumarapp.home.admin.categorias.domain.repository.CategoriaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindCategoriaRepository(impl: CategoriaRepositoryImpl): CategoriaRepository
}
