package com.kristinakoneva.nutritective.domain

import com.kristinakoneva.nutritective.domain.authentication.AuthRepository
import com.kristinakoneva.nutritective.domain.authentication.AuthRepositoryImpl
import com.kristinakoneva.nutritective.domain.foodproducts.FoodProductsRepository
import com.kristinakoneva.nutritective.domain.foodproducts.FoodProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun authRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun foodProductsRepository(foodProductsRepository: FoodProductsRepositoryImpl): FoodProductsRepository
}
