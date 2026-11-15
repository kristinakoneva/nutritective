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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.screens.explorerecipes.composables.RecipeItemCard
import com.kristinakoneva.nutritective.ui.shared.accessibility.moveFocusOnTab
import com.kristinakoneva.nutritective.ui.shared.accessibility.screenHeading
import com.kristinakoneva.nutritective.ui.shared.accessibility.sectionHeading
import com.kristinakoneva.nutritective.ui.shared.accessibility.semanticSection
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.ClearLastSearchConfirmationDialog
import com.kristinakoneva.nutritective.ui.shared.composables.InstructionStep
import com.kristinakoneva.nutritective.ui.shared.models.InstructionSteps
import com.kristinakoneva.nutritective.ui.theme.nutritectiveOutlinedTextFieldColors
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
        Column(modifier = Modifier.semanticSection(0f)) {
            Text(
                text = stringResource(R.string.explore_recipes),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.screenHeading(),
            )
            OutlinedTextField(
                value = searchText.orEmpty(),
                textStyle = TextStyle(color = Color.Black),
                onValueChange = onSearchTextChanged,
                label = { Text(stringResource(R.string.explore_recipes_input_label)) },
                colors = nutritectiveOutlinedTextFieldColors(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = spacing_3)
                    .moveFocusOnTab(focusManager),
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
        }

        if (searchedFor != null) {
            Column(modifier = Modifier.semanticSection(1f)) {
                Text(
                    text = stringResource(R.string.you_searched_for, searchedFor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = spacing_3)
                        .sectionHeading(),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
                if (recipeItems.isNullOrEmpty()) {
                    Text(
                        text = stringResource(R.string.no_recipes_found),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = spacing_2),
                    )
                } else {
                    recipeItems.forEachIndexed { index, item ->
                        Spacer(modifier = Modifier.padding(top = spacing_2))
                        RecipeItemCard(recipeItem = item, listIndex = index) {
                            val intent = CustomTabsIntent.Builder().build()
                            intent.launchUrl(
                                context,
                                Uri.parse(item.recipe.url)
                            )
                        }
                    }
                }
            }
        }

        if (searchedFor == null) {
            Column(modifier = Modifier.semanticSection(2f)) {
                Text(
                    modifier = Modifier
                        .padding(top = spacing_5)
                        .fillMaxSize()
                        .sectionHeading(),
                    text = stringResource(R.string.instructions),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(top = spacing_3))
                ExploreRecipesInstructionSteps()
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .semanticSection(2f),
            ) {
                OutlinedButton(onClick = onClearLastSearchClicked, modifier = Modifier.padding(top = spacing_3)) {
                    Text(text = stringResource(R.string.clear_last_search))
                }
            }
        }
    }
}

@Composable
fun ExploreRecipesInstructionSteps() {
    val steps = InstructionSteps.getExploreRecipesInstructionSteps()
    Column {
        steps.forEachIndexed { index, step ->
            InstructionStep(
                imageResId = step.imageResId,
                descriptionRes = step.descriptionRes,
                stepIndex = index + 1,
                totalSteps = steps.size,
            )
            Spacer(modifier = Modifier.padding(top = spacing_2))
        }
    }
}
