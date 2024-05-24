package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import com.kristinakoneva.nutritective.domain.recipes.models.Recipe

data class ExploreRecipesState(
    val searchText: String? = null,
    val searchedFor: String? = null,
    val recipes: List<Recipe>? = null,
    val selectedRecipe: Recipe? = null
)
