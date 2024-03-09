package com.kristinakoneva.nutritective.ui.screens.analyzetext

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen

@Composable
fun AnalyzeTextScreen(
    viewModel: AnalyzeTextViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) { state ->
        Column {
            Text("Analyze Text")
            TextField(
                value = state.searchText ?: "", onValueChange = { viewModel.onTextChanged(it) },
                label = { Text("Insert text") },
                singleLine = false
            )
            Button(onClick = { viewModel.analyzeText() }) {
                Text("Analyze")
            }
            Text(text = state.name ?: "")
        }
    }
}