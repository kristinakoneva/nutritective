package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodItemsNutritionDataResponse(
    @SerialName("items")
    val foodItems: List<FoodItemResource>
)
