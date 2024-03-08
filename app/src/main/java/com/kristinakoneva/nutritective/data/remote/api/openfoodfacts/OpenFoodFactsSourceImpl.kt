package com.kristinakoneva.nutritective.data.remote.api.openfoodfacts

import com.kristinakoneva.nutritective.data.remote.api.openfoodfacts.models.ProductNutritionDataResponse
import javax.inject.Inject

class OpenFoodFactsSourceImpl @Inject constructor(
    private val apiService: OpenFoodFactsApiService
) : OpenFoodFactsSource {
    override suspend fun getProductNutritionData(barcode: String): ProductNutritionDataResponse =
        apiService.getProductNutritionData(barcode)
}