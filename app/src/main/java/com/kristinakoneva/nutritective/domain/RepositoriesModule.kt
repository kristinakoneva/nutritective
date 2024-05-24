package com.kristinakoneva.nutritective.domain

import com.kristinakoneva.nutritective.domain.authentication.AuthRepository
import com.kristinakoneva.nutritective.domain.authentication.AuthRepositoryImpl
import com.kristinakoneva.nutritective.domain.fooditems.FoodItemsRepository
import com.kristinakoneva.nutritective.domain.fooditems.FoodItemsRepositoryImpl
import com.kristinakoneva.nutritective.domain.foodproducts.FoodProductsRepository
import com.kristinakoneva.nutritective.domain.foodproducts.FoodProductsRepositoryImpl
import com.kristinakoneva.nutritective.domain.recipes.RecipesRepository
import com.kristinakoneva.nutritective.domain.recipes.RecipesRepositoryImpl
import com.kristinakoneva.nutritective.domain.session.SessionRepository
import com.kristinakoneva.nutritective.domain.session.SessionRepositoryImpl
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

    @Binds
    fun foodItemsRepository(foodItemsRepository: FoodItemsRepositoryImpl): FoodItemsRepository

    @Binds
    fun recipesRepository(recipesRepository: RecipesRepositoryImpl): RecipesRepository

    @Binds
    fun sessionRepository(sessionRepository: SessionRepositoryImpl): SessionRepository
}
