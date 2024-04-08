package com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts

import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models.FoodProductNutritionDataResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenFoodFactsApiService {

    @GET("product/{barcode}")
    suspend fun fetchFoodProductByBarcode(@Path("barcode") barcode: String): FoodProductNutritionDataResponse
}
