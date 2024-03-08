package com.kristinakoneva.nutritective.data.remote.api.openfoodfacts

import com.kristinakoneva.nutritective.data.remote.api.openfoodfacts.models.ProductNutritionDataResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenFoodFactsApiService {

    @GET("product/{barcode}")
    suspend fun getProductNutritionData(@Path("barcode") barcode: String): ProductNutritionDataResponse
}