package com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResource(
    @SerialName("title")
    val title: String,
    @SerialName("ingredients")
    val ingredients: String,
    @SerialName("servings")
    val servings: String,
    @SerialName("instructions")
    val instructions: String,
)
