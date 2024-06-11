package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.FoodItemsNutritionDataResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface CalorieNinjasApiService {

    @GET("nutrition")
    suspend fun getNutritionFromText(@Query("query") text: String): FoodItemsNutritionDataResponse

    @Multipart
    @POST("imagetextnutrition")
    @Headers("Transfer-Encoding: chunked")
    suspend fun getNutritionFromImage(@Part image: MultipartBody.Part): FoodItemsNutritionDataResponse
}
