package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.NutritionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CalorieNinjasApiService {


    @GET("nutrition")
    suspend fun getNutrition(@Query("query") text: String): NutritionResponse

}