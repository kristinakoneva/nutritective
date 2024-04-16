package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.FoodItemsNutritionDataResponse
import okhttp3.MultipartBody

interface CalorieNinjasSource {

    suspend fun getNutritionFromText(text: String): FoodItemsNutritionDataResponse

    suspend fun getNutritionFromImage(image: MultipartBody.Part): FoodItemsNutritionDataResponse
}
