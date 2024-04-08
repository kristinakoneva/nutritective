package com.kristinakoneva.nutritective.domain.foodproducts.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nutriment(
    val type: NutrimentType,
    val value: String
) : Parcelable
