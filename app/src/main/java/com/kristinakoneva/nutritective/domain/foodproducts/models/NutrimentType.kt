package com.kristinakoneva.nutritective.domain.foodproducts.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class NutrimentType(
    val label: String,
    val icon: Int
) : Parcelable {
    ENERGY("Energy", 0),
    FAT("Fat", 0),
    SATURATED_FAT("Saturated fat", 0),
    CARBOHYDRATES("Carbohydrates", 0),
    SUGARS("Sugars", 0),
    PROTEINS("Proteins", 0),
    SALT("Salt", 0),
    FIBER("Fiber", 0),
    SODIUM("Sodium", 0)
}
