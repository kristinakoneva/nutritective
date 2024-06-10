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
            if (userAllergenList.isNotEmpty()) {
                val detectedAllergens: List<String> =
                    foodItems.map { foodItem -> foodItem.name }.detectAllergensPresence(userAllergenList)
                val allergenStatus =
                    if (detectedAllergens.isEmpty()) {
                        AllergenStatus.WARNING
                    } else {
                        AllergenStatus.DANGER
                    }

                viewState = viewState.copy(
                    searchText = searchText,
                    searchedFor = searchedFor,
                    foodItems = foodItems,
                    allergenStatus = allergenStatus,
                    detectedAllergens = detectedAllergens
                )
            } else {
                viewState = viewState.copy(
                    searchText = searchText,
                    searchedFor = searchedFor,
                    foodItems = foodItems
                )
            }
        }
    }

    fun onFoodItemClicked(foodItem: FoodItem) {
        viewState = viewState.copy(selectedFoodItem = foodItem)
    }

    fun clearFoodItemSelection() {
        viewState = viewState.copy(selectedFoodItem = null)
    }
}
