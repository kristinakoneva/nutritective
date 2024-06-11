package com.kristinakoneva.nutritective.ui.screens.explorerecipes

data class ExploreRecipesState(
    val searchText: String? = null,
    val searchedFor: String? = null,
    val recipeItems: List<RecipeItem>? = null
)
