package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import com.kristinakoneva.nutritective.domain.recipes.RecipesRepository
import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.extensions.detectAllergensPresence
import com.kristinakoneva.nutritective.ui.screens.analyzetext.AnalyzeTextState
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreRecipesViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository,
    private val userRepository: UserRepository
) : BaseViewModel<ExploreRecipesState, Unit>(ExploreRecipesState()) {

    private var searchText: String = ""

    fun onSearchTextChanged(inputText: String) {
        searchText = inputText
        viewState = viewState.copy(searchText = inputText)
    }

    fun exploreRecipes() {
        launchWithLoading {
            val searchedFor = searchText.trim()
            searchText = ""

            val userAllergensList = userRepository.getUserAllergensList()
            if (userAllergensList.isNotEmpty()) {
                val recipeItems = recipesRepository.exploreRecipes(searchedFor).map { recipe ->
                    val detectedAllergens =
                        recipe.ingredientsList?.detectAllergensPresence(userAllergensList).orEmpty() + recipe.title.detectAllergensPresence(
                            userAllergensList
                        )
                    if (detectedAllergens.isNotEmpty()) {
                        RecipeItem(
                            recipe = recipe,
                            allergenStatus = AllergenStatus.DANGER,
                            detectedAllergens = detectedAllergens.distinct()
                        )
                    } else {
                        RecipeItem(
                            recipe = recipe,
                            allergenStatus = AllergenStatus.WARNING
                        )
                    }
                }

                viewState = ExploreRecipesState(
                    searchText = searchText,
                    searchedFor = searchedFor,
                    recipeItems = recipeItems
                )
            } else {
                viewState = ExploreRecipesState(
                    searchText = searchText,
                    searchedFor = searchedFor,
                    recipeItems = recipesRepository.exploreRecipes(searchedFor).map { recipe -> RecipeItem(recipe = recipe) }
                )
            }
        }
    }

    fun onClearLastSearchClicked() {
        viewState = viewState.copy(showClearLastSearchDialog = true)
    }

    fun onClearLastSearchConfirmed() {
        viewState = ExploreRecipesState(showClearLastSearchDialog = false)
    }

    fun onClearLastSearchCancelled() {
        viewState = viewState.copy(showClearLastSearchDialog = false)
    }
}
