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
    @SerialName("ingredientLines")
    val ingredientLines: List<String>,
    @SerialName("cuisineType")
    val cuisineType: List<String>,
    @SerialName("mealType")
    val mealType: List<String>,
    @SerialName("dishType")
    val dishType: List<String>
)
