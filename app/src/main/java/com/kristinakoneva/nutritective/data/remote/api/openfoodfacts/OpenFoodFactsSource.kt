package com.kristinakoneva.nutritective.data.remote.api.openfoodfacts

import com.kristinakoneva.nutritective.data.remote.api.openfoodfacts.models.ProductNutritionDataResponse

interface OpenFoodFactsSource {
    suspend fun getProductNutritionData(barcode: String): ProductNutritionDataResponse
}