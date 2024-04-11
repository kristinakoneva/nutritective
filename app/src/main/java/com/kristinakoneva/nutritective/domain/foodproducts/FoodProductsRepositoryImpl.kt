package com.kristinakoneva.nutritective.domain.foodproducts

import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.OpenFoodFactsSource
import com.kristinakoneva.nutritective.domain.foodproducts.mappers.toFoodProduct
import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodProductsRepositoryImpl @Inject constructor(
    private val openFoodFactsSource: OpenFoodFactsSource
) : FoodProductsRepository {
    override suspend fun fetchFoodProductByBarcode(barcode: String): FoodProduct? = withContext(Dispatchers.IO) {
        openFoodFactsSource.fetchFoodProductByBarcode(barcode).product?.toFoodProduct()
    }
}
