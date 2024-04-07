package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.NutritionResponse
import javax.inject.Inject
import okhttp3.MultipartBody

class CalorieNinjasSourceImpl @Inject constructor(
    private val apiService: CalorieNinjasApiService
) : CalorieNinjasSource {
    override suspend fun getNutrition(text: String): NutritionResponse = apiService.getNutrition(text)

    override suspend fun getNutritionFromImage(image: MultipartBody.Part) = apiService.getNutritionFromImage(image)
}