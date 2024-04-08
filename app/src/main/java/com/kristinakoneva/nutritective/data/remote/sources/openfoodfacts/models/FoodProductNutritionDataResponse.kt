package com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodProductNutritionDataResponse(
    @SerialName("product")
    val product: FoodProductResource? = null
)
