package com.kristinakoneva.nutritective.domain.foodproducts

import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct

interface FoodProductsRepository {
    suspend fun fetchFoodProductByBarcode(barcode: String): FoodProduct?
}
