package com.kristinakoneva.nutritective.domain.foodproducts.models

import android.os.Parcelable
import com.kristinakoneva.nutritective.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class NutrimentType(
    val label: String,
    val iconRes: Int
) : Parcelable {
    ENERGY("Energy", R.drawable.ic_energy),
    FAT("Fat", R.drawable.ic_fat),
    SATURATED_FAT("Satur. fat", R.drawable.ic_saturated_fat),
    CARBOHYDRATES("Carbs", R.drawable.ic_carbohydrates),
    SUGARS("Sugars", R.drawable.ic_sugars),
    PROTEINS("Proteins", R.drawable.ic_proteins),
    SALT("Salt", R.drawable.ic_salt),
    FIBER("Fiber", R.drawable.ic_fiber),
    SODIUM("Sodium", R.drawable.ic_sodium)
}
