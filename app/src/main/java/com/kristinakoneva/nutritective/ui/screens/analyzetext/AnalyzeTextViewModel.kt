package com.kristinakoneva.nutritective.ui.screens.analyzetext

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.CalorieNinjasSource
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnalyzeTextViewModel @Inject constructor(
    private val source: CalorieNinjasSource
) : BaseViewModel<AnalyzeTextState, Unit>(AnalyzeTextState()) {

    private var searchText: String = "apple"

    fun onTextChanged(text: String) {
        searchText = text
        viewState = AnalyzeTextState(searchText = text)
    }

    fun analyzeText() {
        launch {
            viewState = AnalyzeTextState(name = source.getNutrition(searchText).product.first().name)
        }
    }
}