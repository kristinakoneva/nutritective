package com.kristinakoneva.nutritective.domain.recipes

import com.kristinakoneva.nutritective.data.remote.sources.edamam.EdamamSource
import com.kristinakoneva.nutritective.domain.recipes.mappers.toRecipe
import com.kristinakoneva.nutritective.domain.recipes.models.Recipe
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val edamamSource: EdamamSource
) : RecipesRepository {
    override suspend fun exploreRecipes(query: String): List<Recipe> =
        edamamSource.exploreRecipes(query).hits.map {
            it.recipe.toRecipe()
        }
}

