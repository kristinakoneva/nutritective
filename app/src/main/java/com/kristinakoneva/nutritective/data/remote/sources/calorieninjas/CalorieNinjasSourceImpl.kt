package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.FoodItemsNutritionDataResponse
import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.RecipeResource
import javax.inject.Inject
import okhttp3.MultipartBody

class CalorieNinjasSourceImpl @Inject constructor(
    private val apiService: CalorieNinjasApiService
) : CalorieNinjasSource {
    override suspend fun getNutritionFromText(text: String): FoodItemsNutritionDataResponse = apiService.getNutritionFromText(text)

    override suspend fun getNutritionFromImage(image: MultipartBody.Part) = apiService.getNutritionFromImage(image)

    override suspend fun getRecipesFromText(text: String): List<RecipeResource> = apiService.getRecipesFromText(text)
}
