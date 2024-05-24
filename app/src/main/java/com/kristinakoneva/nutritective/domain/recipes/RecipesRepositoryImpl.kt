package com.kristinakoneva.nutritective.domain.recipes

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.CalorieNinjasSource
import com.kristinakoneva.nutritective.domain.recipes.mappers.toRecipe
import com.kristinakoneva.nutritective.domain.recipes.models.Recipe
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val calorieNinjasSource: CalorieNinjasSource
) : RecipesRepository {
    override suspend fun getRecipesFromText(text: String): List<Recipe> =
        calorieNinjasSource.getRecipesFromText(text).map { it.toRecipe() }
}
