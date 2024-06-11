package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.screens.explorerecipes.composables.RecipeItemCard
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.ClearLastSearchConfirmationDialog
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
            recipeItems = state.recipeItems,
            showClearLastSearchDialog = state.showClearLastSearchDialog,
            onExplore = viewModel::exploreRecipes,
            onSearchTextChanged = viewModel::onSearchTextChanged,
            onClearLastSearchClicked = viewModel::onClearLastSearchClicked,
            onClearLastSearchConfirmed = viewModel::onClearLastSearchConfirmed,
            onClearLastSearchCancelled = viewModel::onClearLastSearchCancelled,
            refresh = viewModel::refresh
        )
    }
}

@Composable
fun ExploreRecipesScreenContent(
    searchText: String?,
    searchedFor: String?,
    recipeItems: List<RecipeItem>?,
    showClearLastSearchDialog: Boolean,
    onExplore: () -> Unit,
    onSearchTextChanged: (String) -> Unit,
    onClearLastSearchClicked: () -> Unit,
    onClearLastSearchConfirmed: () -> Unit,
    onClearLastSearchCancelled: () -> Unit,
    refresh: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

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
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    onExplore()
                }
            )
        )
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
            recipeItems?.forEach {
                Spacer(modifier = Modifier.padding(top = spacing_2))
                RecipeItemCard(recipeItem = it) {
                    val intent = CustomTabsIntent.Builder().build()
                    intent.launchUrl(
                        context,
                        Uri.parse(it.recipe.url)
                    )
                }
            }
        }
        if (recipeItems.isNullOrEmpty() && searchedFor != null) {
            Text(
                text = "No recipes found.",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = spacing_2)
            )
        }

        if (searchedFor == null) {
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
