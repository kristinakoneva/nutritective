package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import com.kristinakoneva.nutritective.domain.recipes.RecipesRepository
import com.kristinakoneva.nutritective.domain.recipes.models.Recipe
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreRecipesViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository
) : BaseViewModel<ExploreRecipesState, Unit>(ExploreRecipesState()) {

    private var searchText: String = ""

    fun onSearchTextChanged(inputText: String) {
        searchText = inputText
        viewState = viewState.copy(searchText = inputText)
    }

    fun exploreRecipes() {
        launchWithLoading {
            val searchedFor = searchText.trim()
            val recipes = recipesRepository.getRecipesFromText(searchedFor)
            searchText = ""
            viewState = viewState.copy(
                searchText = searchText,
                searchedFor = searchedFor,
                recipes = recipes
            )
        }
    }

    fun onRecipeClicked(recipe: Recipe) {
        viewState = viewState.copy(selectedRecipe = recipe)
    }

    fun clearRecipeSelection() {
        viewState = viewState.copy(selectedRecipe = null)
    }
}
