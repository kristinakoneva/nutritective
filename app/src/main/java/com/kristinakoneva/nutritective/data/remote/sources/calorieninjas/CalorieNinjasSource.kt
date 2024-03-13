package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.NutritionResponse
import okhttp3.MultipartBody

interface CalorieNinjasSource {

    suspend fun getNutrition(text: String): NutritionResponse

    suspend fun getNutritionFromImage(image: MultipartBody.Part): NutritionResponse
}