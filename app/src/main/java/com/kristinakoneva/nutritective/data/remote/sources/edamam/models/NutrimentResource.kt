package com.kristinakoneva.nutritective.data.remote.sources.edamam.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutrimentResource(
    @SerialName("label")
    val label: String,
    @SerialName("quantity")
    val quantity: Double,
    @SerialName("unit")
    val unit: String,
)
