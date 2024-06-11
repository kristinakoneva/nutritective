package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import com.kristinakoneva.nutritective.domain.recipes.models.Recipe
import com.kristinakoneva.nutritective.ui.shared.models.AllergenStatus

data class RecipeItem(
    val recipe: Recipe,
    val allergenStatus: AllergenStatus? = null,
    val detectedAllergens: List<String>? = null
)
