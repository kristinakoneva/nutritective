package com.kristinakoneva.nutritective.domain.shared.models

import android.os.Parcelable
import androidx.annotation.StringRes
import com.kristinakoneva.nutritective.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class NutrimentType(
    @StringRes val labelRes: Int,
    val iconRes: Int
) : Parcelable {
    ENERGY(R.string.nutriment_energy, R.drawable.ic_energy),
    FAT(R.string.nutriment_fat, R.drawable.ic_fat),
    SATURATED_FAT(R.string.nutriment_saturated_fat, R.drawable.ic_saturated_fat),
    CARBOHYDRATES(R.string.nutriment_carbs, R.drawable.ic_carbohydrates),
    SUGARS(R.string.nutriment_sugars, R.drawable.ic_sugars),
    PROTEINS(R.string.nutriment_proteins, R.drawable.ic_proteins),
    SALT(R.string.nutriment_salt, R.drawable.ic_salt),
    FIBER(R.string.nutriment_fiber, R.drawable.ic_fiber),
    SODIUM(R.string.nutriment_sodium, R.drawable.ic_sodium),
    POTASSIUM(R.string.nutriment_potassium, R.drawable.ic_potassium),
    CHOLESTEROL(R.string.nutriment_cholesterol, R.drawable.ic_cholesterol),
}
