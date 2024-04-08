package com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResource(
    @SerialName("product_name")
    val productName: String? = null,
    @SerialName("brands")
    val brands: String? = null,
    @SerialName("ingredients_text")
    val ingredientsText: String? = null,
    @SerialName("nutriments")
    val nutriments: NutrimentsResource? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("quantity_value")
    val quantity: Double? = null,
    @SerialName("quantity_unit")
    val quantityUnit: String? = null,
    @SerialName("categories")
    val categories: String? = null,
    @SerialName("nutriscore_grade")
    val nutrientGrade: String? = null,
    @SerialName("allergens")
    val allergens: String? = null
)
