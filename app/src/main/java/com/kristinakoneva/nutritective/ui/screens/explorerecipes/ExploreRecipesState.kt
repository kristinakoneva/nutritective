package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem

data class ExploreRecipesState(
    val searchText: String? = null,
    val searchedFor: String? = null,
    val recipes: List<FoodItem>? = null,
    val selectedRecipe: FoodItem? = null
)
