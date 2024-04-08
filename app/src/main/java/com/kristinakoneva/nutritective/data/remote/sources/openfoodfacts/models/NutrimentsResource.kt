package com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutrimentsResource(
    @SerialName("energy_value")
    val energyValue: Double? = null,
    @SerialName("energy_unit")
    val energyUnit: String? = null,
    @SerialName("fat_value")
    val fatValue: Double? = null,
    @SerialName("fat_unit")
    val fatUnit: String? = null,
    @SerialName("saturated_fat_value")
    val saturatedFatValue: Double? = null,
    @SerialName("saturated_fat_unit")
    val saturatedFatUnit: String? = null,
    @SerialName("carbohydrates_value")
    val carbohydratesValue: Double? = null,
    @SerialName("carbohydrates_unit")
    val carbohydratesUnit: String? = null,
    @SerialName("sugars_value")
    val sugarsValue: Double? = null,
    @SerialName("sugars_unit")
    val sugarsUnit: String? = null,
    @SerialName("fiber_value")
    val fiberValue: Double? = null,
    @SerialName("fiber_unit")
    val fiberUnit: String? = null,
    @SerialName("proteins_value")
    val proteinsValue: Double? = null,
    @SerialName("proteins_unit")
    val proteinsUnit: String? = null,
    @SerialName("salt_value")
    val saltValue: Double? = null,
    @SerialName("salt_unit")
    val saltUnit: String? = null,
    @SerialName("sodium_value")
    val sodiumValue: Double? = null,
    @SerialName("sodium_unit")
    val sodiumUnit: String? = null
)
