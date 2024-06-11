package com.kristinakoneva.nutritective.data.remote.sources.edamam.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResource(
    @SerialName("label")
    val label: String,
    @SerialName("image")
    val image: String? = null,
    @SerialName("source")
    val source: String,
    @SerialName("url")
    val url: String,
    @SerialName("ingredientLines")
    val ingredientLines: List<String>? = null,
    @SerialName("cuisineType")
    val cuisineType: List<String>? = null,
    @SerialName("mealType")
    val mealType: List<String>? = null,
    @SerialName("dishType")
    val dishType: List<String>? = null,
)
