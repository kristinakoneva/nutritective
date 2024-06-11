package com.kristinakoneva.nutritective.ui.screens.scanbarcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import com.kristinakoneva.nutritective.ui.screens.scanbarcode.composables.FoodProductCard
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.ClearLastSearchConfirmationDialog
import com.kristinakoneva.nutritective.ui.shared.composables.InstructionStep
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus
import com.kristinakoneva.nutritective.ui.shared.utils.InstructionSteps
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_8

@Composable
fun ScanBarcodeScreen(
    viewModel: ScanBarcodeViewModel = hiltViewModel(),
    onNavigateToOpenCamera: () -> Unit,
    onNavigateToFoodProductDetails: () -> Unit
) {
    BaseScreen(viewModel = viewModel, eventHandler = {
        when (it) {
            is ScanBarcodeEvent.NavigateToOpenCamera -> onNavigateToOpenCamera()
            is ScanBarcodeEvent.NavigateToFoodProductDetails -> onNavigateToFoodProductDetails()
        }
    }) { state ->
        ScanBarcodeScreenContent(
            onScanBarcodeButtonClicked = viewModel::onScanBarcodeButtonClicked,
            lastSearchedFoodProduct = state.lastSearch,
            allergenStatus = state.allergenStatus,
            detectedAllergens = state.detectedAllergens,
            shouldShowClearLastSearchDialog = state.showClearLastSearchDialog,
            onNavigateToFoodProductDetails = viewModel::onNavigateToFoodProductDetails,
            refresh = viewModel::refresh,
            onClearLastSearchClicked = viewModel::onClearLastSearchClicked,
            onClearLastSearchConfirmed = viewModel::onClearLastSearchConfirmed,
            onClearLastSearchCancelled = viewModel::onClearLastSearchCancelled
        )
    }
}

@Composable
fun ScanBarcodeScreenContent(
    lastSearchedFoodProduct: FoodProduct? = null,
    allergenStatus: AllergenStatus? = null,
    detectedAllergens: List<String>? = null,
    shouldShowClearLastSearchDialog: Boolean = false,
    onScanBarcodeButtonClicked: () -> Unit,
    onNavigateToFoodProductDetails: () -> Unit,
    refresh: () -> Unit,
    onClearLastSearchClicked: () -> Unit,
    onClearLastSearchConfirmed: () -> Unit,
    onClearLastSearchCancelled: () -> Unit
) {
    LaunchedEffect(Unit) {
        refresh()
    }

    if (shouldShowClearLastSearchDialog) {
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
            text = "Scan a food product's barcode",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier
                .padding(top = spacing_3)
                .fillMaxWidth(),
            onClick = onScanBarcodeButtonClicked,
            contentPadding = PaddingValues(spacing_2)
        ) {
            Text(text = "Scan barcode".uppercase())
        }

        if (lastSearchedFoodProduct != null) {
            Text(
                text = "Your last search:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing_3)
                    .padding(bottom = spacing_2),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            FoodProductCard(
                foodProduct = lastSearchedFoodProduct,
                onClickAction = onNavigateToFoodProductDetails,
                allergenStatus = allergenStatus,
                detectedAllergens = detectedAllergens
            )
        }

        if (lastSearchedFoodProduct == null) {
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
            ScanBarcodeInstructionSteps()
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
fun ScanBarcodeInstructionSteps() {
    Column {
        InstructionSteps.getScanBarcodeInstructionSteps().forEach { step ->
            InstructionStep(
                imageResId = step.imageResId,
                description = step.description
            )
            Spacer(modifier = Modifier.padding(top = spacing_2))
        }
        Text(
            modifier = Modifier.padding(top = spacing_2), text = "Alternative", style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(top = spacing_3))
        InstructionStep(
            imageResId = R.drawable.illustration_alternative,
            description = "Alternatively, you can manually enter the barcode of the food product and get the nutritional information."
        )
    }
}
