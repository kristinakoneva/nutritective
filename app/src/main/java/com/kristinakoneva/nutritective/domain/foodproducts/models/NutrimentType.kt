package com.kristinakoneva.nutritective.domain.foodproducts.models

import android.os.Parcelable
import com.kristinakoneva.nutritective.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class NutrimentType(
    val label: String,
    val iconRes: Int
) : Parcelable {
    ENERGY("Energy", R.drawable.salt_shaker),
    FAT("Fat", R.drawable.salt_shaker),
    SATURATED_FAT("Saturated fat", R.drawable.salt_shaker),
    CARBOHYDRATES("Carbohydrates", R.drawable.salt_shaker),
    SUGARS("Sugars", R.drawable.salt_shaker),
    PROTEINS("Proteins", R.drawable.salt_shaker),
    SALT("Salt", R.drawable.salt_shaker),
    FIBER("Fiber", R.drawable.salt_shaker),
    SODIUM("Sodium", R.drawable.salt_shaker)
}
