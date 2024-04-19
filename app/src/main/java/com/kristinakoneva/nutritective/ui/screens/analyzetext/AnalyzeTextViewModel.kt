package com.kristinakoneva.nutritective.ui.screens.analyzetext

import com.kristinakoneva.nutritective.domain.fooditems.FoodItemsRepository
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnalyzeTextViewModel @Inject constructor(
    private val foodItemsRepository: FoodItemsRepository
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
            viewState = viewState.copy(
                searchText = searchText,
                searchedFor = searchedFor,
                foodItems = foodItems
            )
        }
    }

    fun onFoodItemClicked(foodItem: FoodItem) {
        viewState = viewState.copy(selectedFoodItem = foodItem)
    }

    fun clearFoodItemSelection() {
        viewState = viewState.copy(selectedFoodItem = null)
    }
}
