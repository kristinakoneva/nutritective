package com.kristinakoneva.nutritective.data.remote.sources.edamam.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResource(
    @SerialName("label")
    val label: String,
    @SerialName("image")
    val image: String,
    @SerialName("source")
    val source: String,
    @SerialName("url")
    val url: String,
    @SerialName("dietLabels")
    val dietLabels: List<String>,
    @SerialName("healthLabels")
    val healthLabels: List<String>,
    @SerialName("cautions")
    val cautions: List<String>,
    @SerialName("ingredientLines")
    val ingredientLines: List<String>,
    @SerialName("calories")
    val calories: Double,
    @SerialName("totalTime")
    val totalTime: Double,
    @SerialName("cuisineType")
    val cuisineType: List<String>,
    @SerialName("mealType")
    val mealType: List<String>,
    @SerialName("dishType")
    val dishType: List<String>,
    @SerialName("totalNutrients")
    val totalNutrients: Map<String, NutrimentResource>,
)
