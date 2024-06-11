package com.kristinakoneva.nutritective.ui.screens.analyzetext

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.AllergenStatusCard
import com.kristinakoneva.nutritective.ui.shared.composables.ClearLastSearchConfirmationDialog
import com.kristinakoneva.nutritective.ui.shared.composables.FoodItemCard
import com.kristinakoneva.nutritective.ui.shared.composables.FoodItemDetailsDialog
import com.kristinakoneva.nutritective.ui.shared.composables.InstructionStep
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus
import com.kristinakoneva.nutritective.ui.shared.utils.InstructionSteps
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_5
import com.kristinakoneva.nutritective.ui.theme.spacing_8

@Composable
fun AnalyzeTextScreen(
    viewModel: AnalyzeTextViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) { state ->
        AnalyzeTextScreenContent(
            searchText = state.searchText,
            searchedFor = state.searchedFor,
            foodItems = state.foodItems,
            selectedFoodItem = state.selectedFoodItem,
            allergenStatus = state.allergenStatus,
            detectedAllergens = state.detectedAllergens,
            showClearLastSearchDialog = state.showClearLastSearchDialog,
            onAnalyzeButtonClick = viewModel::analyzeText,
            onSearchTextChanged = viewModel::onSearchTextChanged,
            onFoodItemClicked = viewModel::onFoodItemClicked,
            clearFoodItemSelection = viewModel::clearFoodItemSelection,
            onClearLastSearchClicked = viewModel::onClearLastSearchClicked,
            onClearLastSearchConfirmed = viewModel::onClearLastSearchConfirmed,
            onClearLastSearchCancelled = viewModel::onClearLastSearchCancelled,
            refresh = viewModel::refresh
        )
    }
}

@Composable
fun AnalyzeTextScreenContent(
    searchText: String?,
    searchedFor: String?,
    foodItems: List<FoodItem>?,
    selectedFoodItem: FoodItem? = null,
    allergenStatus: AllergenStatus? = null,
    detectedAllergens: List<String>? = null,
    showClearLastSearchDialog: Boolean,
    onAnalyzeButtonClick: () -> Unit,
    onSearchTextChanged: (String) -> Unit,
    onFoodItemClicked: (FoodItem) -> Unit,
    clearFoodItemSelection: () -> Unit,
    onClearLastSearchClicked: () -> Unit,
    onClearLastSearchConfirmed: () -> Unit,
    onClearLastSearchCancelled: () -> Unit,
    refresh: () -> Unit
) {
    LaunchedEffect(Unit) {
        refresh()
    }

    if (showClearLastSearchDialog) {
        ClearLastSearchConfirmationDialog(
            onConfirm = onClearLastSearchConfirmed,
            onCancel = onClearLastSearchCancelled
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(spacing_3)
            .padding(bottom = spacing_8),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedFoodItem != null) {
            Spacer(modifier = Modifier.padding(top = spacing_3))
            FoodItemDetailsDialog(foodItem = selectedFoodItem, onClose = clearFoodItemSelection)
        }


        Text(
            text = "Analyze food related text",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = searchText.orEmpty(),
            textStyle = TextStyle(color = Color.Black),
            onValueChange = onSearchTextChanged,
            label = { Text("Enter search text") },
            modifier = Modifier
                .fillMaxSize()
                .padding(top = spacing_3),
            maxLines = 5,
        )
        Button(modifier = Modifier.padding(top = spacing_2), onClick = onAnalyzeButtonClick) {
            Text("Analyze")
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

        if (allergenStatus != null && !foodItems.isNullOrEmpty()) {
            Column(modifier = Modifier.padding(top = spacing_2)) {
                AllergenStatusCard(allergenStatus = allergenStatus, detectedAllergens = detectedAllergens)
            }
        }

        foodItems?.forEach {
            Spacer(modifier = Modifier.padding(top = spacing_2))
            FoodItemCard(foodItem = it, onClickAction = onFoodItemClicked)
        }

        if (foodItems.isNullOrEmpty() && searchedFor != null) {
            Text(
                text = "No food items found.",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = spacing_2)
            )
        }

        if (foodItems == null) {
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
            AnalyzeTextInstructionSteps()
        } else {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(onClick = onClearLastSearchClicked, modifier = Modifier.padding(top = spacing_3)) {
                    Text(text = "Clear last search")
                }
            }
        }
    }
}

@Composable
fun AnalyzeTextInstructionSteps() {
    Column {
        InstructionSteps.getAnalyzeTextInstructionSteps().forEach { step ->
            InstructionStep(
                imageResId = step.imageResId,
                description = step.description
            )
            Spacer(modifier = Modifier.padding(top = spacing_2))
        }
    }
}
