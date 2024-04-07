package com.kristinakoneva.nutritective.domain

import com.kristinakoneva.nutritective.domain.authentication.AuthRepository
import com.kristinakoneva.nutritective.domain.authentication.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun authRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}
