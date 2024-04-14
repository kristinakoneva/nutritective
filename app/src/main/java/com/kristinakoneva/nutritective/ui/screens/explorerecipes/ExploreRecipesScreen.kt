package com.kristinakoneva.nutritective.ui.screens.explorerecipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.InstructionStep
import com.kristinakoneva.nutritective.ui.shared.utils.InstructionSteps
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_8

@Composable
fun ExploreRecipesScreen(
    viewModel: ExploreRecipesViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(spacing_3)
                .padding(bottom = spacing_8),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Explore Recipes")
            Text(
                modifier = Modifier
                    .padding(top = spacing_3)
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
