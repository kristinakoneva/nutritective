package com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts

import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models.FoodProductNutritionDataResponse
import javax.inject.Inject

class OpenFoodFactsSourceImpl @Inject constructor(
    private val apiService: OpenFoodFactsApiService
) : OpenFoodFactsSource {
    override suspend fun fetchFoodProductByBarcode(barcode: String): FoodProductNutritionDataResponse =
        apiService.fetchFoodProductByBarcode(barcode)
}
