package com.kristinakoneva.nutritective.data.remote.sources.edamam

import com.kristinakoneva.nutritective.BuildConfig
import com.kristinakoneva.nutritective.data.remote.sources.edamam.models.ExploreRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamApiService {

    @GET("recipes/v2")
    suspend fun exploreRecipes(
        @Query("app_id") appId: String = BuildConfig.EDAMAM_APP_ID,
        @Query("app_key") appKey: String = BuildConfig.EDAMAM_APP_KEY,
        @Query("type") type: String = "public",
        @Query("q") query: String
    ): ExploreRecipesResponse
}
