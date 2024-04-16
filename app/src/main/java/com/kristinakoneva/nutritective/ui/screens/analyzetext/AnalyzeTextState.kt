package com.kristinakoneva.nutritective.ui.screens.analyzetext

import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem

data class AnalyzeTextState(
    val searchText: String? = null,
    val foodProducts: List<FoodItem>? = null
)
