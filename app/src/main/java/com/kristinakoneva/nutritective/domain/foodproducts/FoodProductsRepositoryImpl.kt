package com.kristinakoneva.nutritective.domain.foodproducts

import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.OpenFoodFactsSource
import com.kristinakoneva.nutritective.domain.foodproducts.mappers.toFoodProduct
import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import javax.inject.Inject

class FoodProductsRepositoryImpl @Inject constructor(
    private val openFoodFactsSource: OpenFoodFactsSource
) : FoodProductsRepository {
    override suspend fun fetchFoodProductByBarcode(barcode: String): FoodProduct? =
        openFoodFactsSource.fetchFoodProductByBarcode(barcode).product?.toFoodProduct()
}
