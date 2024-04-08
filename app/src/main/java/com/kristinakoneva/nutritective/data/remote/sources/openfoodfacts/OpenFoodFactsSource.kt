package com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts

import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models.FoodProductNutritionDataResponse

interface OpenFoodFactsSource {
    suspend fun fetchFoodProductByBarcode(barcode: String): FoodProductNutritionDataResponse
}
