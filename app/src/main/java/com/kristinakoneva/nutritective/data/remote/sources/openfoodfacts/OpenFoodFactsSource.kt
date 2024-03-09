package com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts

import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models.ProductNutritionDataResponse

interface OpenFoodFactsSource {
    suspend fun getProductNutritionData(barcode: String): ProductNutritionDataResponse
}