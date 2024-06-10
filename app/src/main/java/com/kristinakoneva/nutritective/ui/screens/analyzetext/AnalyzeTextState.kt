package com.kristinakoneva.nutritective.ui.screens.analyzetext

import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus

data class AnalyzeTextState(
    val searchText: String? = null,
    val searchedFor: String? = null,
    val foodItems: List<FoodItem>? = null,
    val selectedFoodItem: FoodItem? = null,
    val allergenStatus: AllergenStatus? = null,
    val detectedAllergens: List<String>? = null
)
