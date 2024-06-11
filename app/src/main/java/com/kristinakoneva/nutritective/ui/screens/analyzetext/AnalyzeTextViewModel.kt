package com.kristinakoneva.nutritective.ui.screens.analyzetext

import com.kristinakoneva.nutritective.domain.fooditems.FoodItemsRepository
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.extensions.detectAllergensPresence
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnalyzeTextViewModel @Inject constructor(
    private val foodItemsRepository: FoodItemsRepository,
    private val userRepository: UserRepository
) : BaseViewModel<AnalyzeTextState, Unit>(AnalyzeTextState()) {

    private var searchText: String = ""

    fun onSearchTextChanged(inputText: String) {
        searchText = inputText
        viewState = viewState.copy(searchText = inputText)
    }

    fun analyzeText() {
        launchWithLoading {
            val searchedFor = searchText.trim()
            val foodItems = foodItemsRepository.getNutritionFromText(searchedFor)
            searchText = ""
            val userAllergenList = userRepository.getUserAllergensList()

            detectAllergens(searchedFor, foodItems, userAllergenList)
        }
    }

    fun onFoodItemClicked(foodItem: FoodItem) {
        viewState = viewState.copy(selectedFoodItem = foodItem)
    }

    fun clearFoodItemSelection() {
        viewState = viewState.copy(selectedFoodItem = null)
    }

    fun onClearLastSearchClicked() {
        viewState = viewState.copy(showClearLastSearchDialog = true)
    }

    fun onClearLastSearchConfirmed() {
        viewState = AnalyzeTextState(showClearLastSearchDialog = false)
    }

    fun onClearLastSearchCancelled() {
        viewState = viewState.copy(showClearLastSearchDialog = false)
    }

    private fun detectAllergens(searchedFor: String, foodItems: List<FoodItem>, userAllergensList: List<String>) {
        if (userAllergensList.isNotEmpty()) {
            val detectedAllergens: List<String> =
                foodItems.map { foodItem -> foodItem.name }.detectAllergensPresence(userAllergensList)
            val allergenStatus =
                if (detectedAllergens.isEmpty()) {
                    AllergenStatus.WARNING
                } else {
                    AllergenStatus.DANGER
                }

            viewState = AnalyzeTextState(
                searchText = searchText,
                searchedFor = searchedFor,
                foodItems = foodItems,
                allergenStatus = allergenStatus,
                detectedAllergens = detectedAllergens.distinct()
            )
        } else {
            viewState = AnalyzeTextState(
                searchText = searchText,
                searchedFor = searchedFor,
                foodItems = foodItems
            )
        }
    }

    fun refresh() {
        if (viewState.searchedFor != null && !viewState.foodItems.isNullOrEmpty()
            && viewState.showClearLastSearchDialog.not() && viewState.selectedFoodItem == null
        )
            launchWithLoading {
                detectAllergens(
                    viewState.searchedFor.orEmpty(),
                    viewState.foodItems.orEmpty(),
                    userRepository.getUserAllergensList()
                )
            }
    }
}
