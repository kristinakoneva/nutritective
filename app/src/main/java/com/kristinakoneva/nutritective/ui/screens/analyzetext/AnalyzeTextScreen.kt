package com.kristinakoneva.nutritective.ui.screens.analyzetext

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen

@Composable
fun AnalyzeTextScreen(
    viewModel: AnalyzeTextViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) {
        Column {
            Text(text = "Analyze Text Screen")
        }
    }
}