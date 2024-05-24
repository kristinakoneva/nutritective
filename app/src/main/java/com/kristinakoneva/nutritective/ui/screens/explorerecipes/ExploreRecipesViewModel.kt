package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreRecipesViewModel @Inject constructor() : BaseViewModel<ExploreRecipesState, Unit>(ExploreRecipesState()) {

    private var searchText: String = ""

    fun onSearchTextChanged(inputText: String) {
        searchText = inputText
        viewState = viewState.copy(searchText = inputText)
    }

    fun exploreRecipes() {
        launchWithLoading {
            // TODO: Implement fetching recipes
        }
    }

    fun onRecipeClicked(recipe: FoodItem) {
        viewState = viewState.copy(selectedRecipe = recipe)
    }

    fun clearRecipeSelection() {
        viewState = viewState.copy(selectedRecipe = null)
    }
}
