package com.kristinakoneva.nutritective.ui.screens.foodproductdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen

@Composable
fun FoodProductDetailsScreen(
    viewModel: FoodProductDetailsViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) { state ->
        when (state) {
            is FoodProductDetailsState.Initial -> Unit
            is FoodProductDetailsState.Content -> {
                Text(text = state.foodProduct.toString())
            }
        }
    }
}
