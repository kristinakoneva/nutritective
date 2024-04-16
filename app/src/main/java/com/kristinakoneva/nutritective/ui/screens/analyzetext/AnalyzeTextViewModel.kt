package com.kristinakoneva.nutritective.ui.screens.analyzetext

import com.kristinakoneva.nutritective.domain.fooditems.FoodItemsRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnalyzeTextViewModel @Inject constructor(
    private val foodItemsRepository: FoodItemsRepository
) : BaseViewModel<AnalyzeTextState, Unit>(AnalyzeTextState()) {

    private var searchText: String = "apple"

    fun onTextChanged(text: String) {
        searchText = text
        viewState = AnalyzeTextState(searchText = text)
    }

    fun analyzeText() {
        launchWithLoading {
            viewState = AnalyzeTextState(foodItems = foodItemsRepository.getNutritionFromText(searchText))
        }
    }
}
