package com.kristinakoneva.nutritective.ui.screens.selectallergens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.AllergenSelectionItem
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_4
import com.kristinakoneva.nutritective.ui.theme.spacing_8

@Composable
fun SelectAllergensScreen(
    viewModel: SelectAllergensViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) { state ->
        SelectAllergensScreenContent(state)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SelectAllergensScreenContent(
    state: SelectAllergensState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Select Allergens",
                        maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "Back button")
                    }
                }
            )
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .padding(top = spacing_8, bottom = spacing_2)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing_2)
                    .padding(horizontal = spacing_3),
                horizontalArrangement = Arrangement.SpaceBetween,
                maxItemsInEachRow = 3
            ) {
                val itemModifier = Modifier
                    .weight(1f)
                    .padding(spacing_1)
                state.commonAllergens.forEach { allergen ->
                    AllergenSelectionItem(
                        name = allergen.text,
                        iconRes = allergen.icon,
                        isSelected = state.selectedCommonAllergens.contains(allergen),
                        modifier = itemModifier
                    )
                }
            }
            Text(
                text = "Manually add allergens:",
                modifier = Modifier
                    .padding(top = spacing_4)
                    .padding(horizontal = spacing_3),
                style = MaterialTheme.typography.titleLarge
            )
            OutlinedTextField(
                value = "",
                textStyle = TextStyle(color = Color.Black),
                onValueChange = {},
                label = { Text("Enter the allergen name") },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = spacing_3)
                    .padding(horizontal = spacing_3),
                maxLines = 2,
            )
            Button(modifier = Modifier
                .padding(top = spacing_2)
                .padding(horizontal = spacing_3), onClick = {}) {
                Text("Add allergen")
            }
        }
    }
}

