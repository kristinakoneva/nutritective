package com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductNutritionDataResponse(
    @SerialName("product")
    val product: Product
)

@Serializable
data class Product(
    @SerialName("product_name")
    val productName: String
)