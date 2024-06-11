package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import com.kristinakoneva.nutritective.domain.recipes.RecipesRepository
import com.kristinakoneva.nutritective.domain.recipes.models.Recipe
import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.extensions.detectAllergensPresence
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import com.kristinakoneva.nutritective.ui.shared.models.AllergenStatus
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

            detectAllergens(
                searchedFor,
                recipes = recipesRepository.exploreRecipes(searchedFor),
                userAllergensList = userRepository.getUserAllergensList()
            )
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

    fun refresh() {
        if (viewState.searchedFor != null && !viewState.recipeItems.isNullOrEmpty() && viewState.showClearLastSearchDialog.not()) {
            launchWithLoading {
                detectAllergens(
                    viewState.searchedFor.orEmpty(),
                    recipes = viewState.recipeItems.orEmpty().map { it.recipe },
                    userAllergensList = userRepository.getUserAllergensList()
                )
            }
        }
    }

    private fun detectAllergens(searchedFor: String, recipes: List<Recipe>, userAllergensList: List<String>) {
        if (userAllergensList.isNotEmpty()) {
            val recipeItems = recipes.map { recipe ->
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
                recipeItems = recipes.map { recipe -> RecipeItem(recipe = recipe) }
            )
        }
    }
}
