package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen

@Composable
fun ExploreRecipesScreen(
    viewModel: ExploreRecipesViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) {
        Text(text = "Explore Recipes Screen")
    }
}
