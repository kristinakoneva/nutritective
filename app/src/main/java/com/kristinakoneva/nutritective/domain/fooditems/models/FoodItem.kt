package com.kristinakoneva.nutritective.domain.fooditems.models

import com.kristinakoneva.nutritective.domain.shared.models.Nutriment
import javax.annotation.concurrent.Immutable

@Immutable
data class FoodItem(
    val name: String,
    val calories: String,
    val servingSize: String,
    val nutriments: List<Nutriment>
)
