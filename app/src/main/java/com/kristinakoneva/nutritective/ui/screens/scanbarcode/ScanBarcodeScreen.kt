package com.kristinakoneva.nutritective.ui.screens.scanbarcode

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import com.kristinakoneva.nutritective.ui.screens.scanbarcode.composables.FoodProductCard
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
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
            ScanBarcodeEvent.NavigateToOpenCamera -> onNavigateToOpenCamera()
        }
    }) { state ->
        ScanBarcodeScreenContent(
            onScanBarcodeButtonClicked = viewModel::onScanBarcodeButtonClicked,
            lastSearchedFoodProduct = state.lastSearch,
            allergenStatus = state.allergenStatus,
            detectedAllergens = state.detectedAllergens,
            onNavigateToFoodProductDetails = onNavigateToFoodProductDetails
        )
    }
}

@Composable
fun ScanBarcodeScreenContent(
    lastSearchedFoodProduct: FoodProduct? = null,
    allergenStatus: AllergenStatus? = null,
    detectedAllergens: List<String>? = null,
    onScanBarcodeButtonClicked: () -> Unit,
    onNavigateToFoodProductDetails: () -> Unit
) {
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
        Button(modifier = Modifier.padding(top = spacing_3), onClick = onScanBarcodeButtonClicked) {
            Text(text = "Scan barcode")
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
