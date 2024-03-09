package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.NutritionResponse

interface CalorieNinjasSource {

    suspend fun getNutrition(text: String): NutritionResponse
}