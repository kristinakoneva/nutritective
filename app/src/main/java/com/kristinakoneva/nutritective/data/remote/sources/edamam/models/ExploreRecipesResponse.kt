package com.kristinakoneva.nutritective.data.remote.sources.edamam.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExploreRecipesResponse(
    @SerialName("hits")
    val hits: List<HitResource>
)
