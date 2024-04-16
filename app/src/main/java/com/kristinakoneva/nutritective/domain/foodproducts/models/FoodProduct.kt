package com.kristinakoneva.nutritective.domain.foodproducts.models

import com.kristinakoneva.nutritective.domain.shared.models.Nutriment

data class FoodProduct(
    val name: String?,
    val ingredients: String?,
    val imageUrl: String?,
    val brands: String?,
    val nutriscoreUrl: String?,
    val nutriments: List<Nutriment>?,
    val allergens: List<String>?,
    val categories: String?,
    val hasAllergensInOtherLanguages: Boolean
)
