package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutritionResponse(
    @SerialName("items")
    val product: List<Item>
)

@Serializable
data class Item(
    @SerialName("name")
    val name: String
)