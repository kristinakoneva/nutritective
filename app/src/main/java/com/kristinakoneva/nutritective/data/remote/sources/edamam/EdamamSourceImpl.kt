package com.kristinakoneva.nutritective.data.remote.sources.edamam

import com.kristinakoneva.nutritective.data.remote.sources.edamam.models.ExploreRecipesResponse
import javax.inject.Inject

class EdamamSourceImpl @Inject constructor(
    private val apiService: EdamamApiService
) : EdamamSource {
    override suspend fun exploreRecipes(query: String): ExploreRecipesResponse = apiService.exploreRecipes(query = query)
}
