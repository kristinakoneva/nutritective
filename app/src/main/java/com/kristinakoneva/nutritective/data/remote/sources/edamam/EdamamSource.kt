package com.kristinakoneva.nutritective.data.remote.sources.edamam

import com.kristinakoneva.nutritective.data.remote.sources.edamam.models.ExploreRecipesResponse

interface EdamamSource {

    suspend fun exploreRecipes(query: String): ExploreRecipesResponse
}
