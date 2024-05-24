package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.domain.recipes.models.Recipe
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.InstructionStep
import com.kristinakoneva.nutritective.ui.shared.utils.InstructionSteps
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_5
import com.kristinakoneva.nutritective.ui.theme.spacing_8

@Composable
fun ExploreRecipesScreen(
    viewModel: ExploreRecipesViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) { state ->
        ExploreRecipesScreenContent(
            searchText = state.searchText,
            searchedFor = state.searchedFor,
            recipes = state.recipes,
            selectedRecipe = state.selectedRecipe,
            onExploreButtonClick = viewModel::exploreRecipes,
            onSearchTextChanged = viewModel::onSearchTextChanged,
            onRecipeClicked = viewModel::onRecipeClicked,
            clearRecipeSelection = viewModel::clearRecipeSelection
        )
    }
}

@Composable
fun ExploreRecipesScreenContent(
    searchText: String?,
    searchedFor: String?,
    recipes: List<Recipe>?,
    selectedRecipe: Recipe? = null,
    onExploreButtonClick: () -> Unit,
    onSearchTextChanged: (String) -> Unit,
    onRecipeClicked: (Recipe) -> Unit,
    clearRecipeSelection: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(spacing_3)
            .padding(bottom = spacing_8),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedRecipe != null) {
            Spacer(modifier = Modifier.padding(top = spacing_3))
            //FoodItemDetailsDialog(foodItem = selectedRecipe, onClose = clearRecipeSelection)
        }


        Text(
            text = "Explore recipes",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = searchText.orEmpty(),
            textStyle = TextStyle(color = Color.Black),
            onValueChange = onSearchTextChanged,
            label = { Text("Enter dish name") },
            modifier = Modifier
                .fillMaxSize()
                .padding(top = spacing_3),
            maxLines = 5,
        )
        Button(modifier = Modifier.padding(top = spacing_2), onClick = onExploreButtonClick) {
            Text("Explore")
        }
        if (searchedFor != null) {
            Text(
                text = "You searched for: \"$searchedFor\"",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing_3),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
        }

        recipes?.forEach {
            Spacer(modifier = Modifier.padding(top = spacing_2))
            Text(text = it.title)
        }

        //        foodItems?.forEach {
        //            Spacer(modifier = Modifier.padding(top = spacing_2))
        //            FoodItemCard(foodItem = it, onClickAction = onFoodItemClicked)
        //        }

        if (recipes.isNullOrEmpty() && searchedFor != null) {
            Text(
                text = "No recipes found",
                modifier = Modifier.padding(top = spacing_2),
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        Text(
            modifier = Modifier
                .padding(top = spacing_5)
                .fillMaxSize(),
            text = "Instructions",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(top = spacing_3))
        ExploreRecipesInstructionSteps()
    }
}

@Composable
fun ExploreRecipesInstructionSteps() {
    Column {
        InstructionSteps.getExploreRecipesInstructionSteps().forEach { step ->
            InstructionStep(
                imageResId = step.imageResId,
                description = step.description
            )
            Spacer(modifier = Modifier.padding(top = spacing_2))
        }
    }
}
