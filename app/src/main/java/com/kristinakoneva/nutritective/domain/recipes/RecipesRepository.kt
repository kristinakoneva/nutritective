package com.kristinakoneva.nutritective.domain.recipes

import com.kristinakoneva.nutritective.domain.recipes.models.Recipe

interface RecipesRepository {

    suspend fun getRecipesFromText(text: String): List<Recipe>
}
