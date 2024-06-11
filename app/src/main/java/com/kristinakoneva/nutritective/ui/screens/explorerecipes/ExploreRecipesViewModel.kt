package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import android.util.Log
import com.kristinakoneva.nutritective.data.remote.sources.edamam.EdamamSource
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
            try{
                val result = recipesRepository.exploreRecipes(searchedFor)
                viewState = viewState.copy(
                    searchedFor = result.toString(),
                    recipes = null
                )
            } catch (e: Exception) {
                Log.e("ExploreRecipesViewModel", "Error exploring recipes: ${e.message}", e)
                Log.e("ExploreRecipesViewModel", "Error exploring recipes: ${e.stackTrace}", e)
                viewState = viewState.copy(
                    searchedFor = "No recipes found",
                    recipes = null
                )
                return@launchWithLoading
            }
        }
    }

    fun onRecipeClicked(recipe: Recipe) {
        viewState = viewState.copy(selectedRecipe = recipe)
    }

    fun clearRecipeSelection() {
        viewState = viewState.copy(selectedRecipe = null)
    }
}
