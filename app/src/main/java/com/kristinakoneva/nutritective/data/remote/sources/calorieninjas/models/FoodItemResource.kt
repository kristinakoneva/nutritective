package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodItemResource(
    @SerialName("name")
    val name: String,
    @SerialName("calories")
    val calories: Double,
    @SerialName("serving_size_g")
    val servingSizeG: Double,
    @SerialName("fat_total_g")
    val fatTotalG: Double,
    @SerialName("fat_saturated_g")
    val fatSaturatedG: Double,
    @SerialName("protein_g")
    val proteinG: Double,
    @SerialName("sodium_mg")
    val sodiumMg: Double,
    @SerialName("potassium_mg")
    val potassiumMg: Double,
    @SerialName("cholesterol_mg")
    val cholesterolMg: Double,
    @SerialName("carbohydrates_total_g")
    val carbohydratesTotalG: Double,
    @SerialName("fiber_g")
    val fiberG: Double,
    @SerialName("sugar_g")
    val sugarG: Double
)
