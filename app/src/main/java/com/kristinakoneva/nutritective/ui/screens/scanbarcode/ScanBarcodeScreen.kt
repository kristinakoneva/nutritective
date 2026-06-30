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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.kristinakoneva.nutritective.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import com.kristinakoneva.nutritective.ui.screens.scanbarcode.composables.FoodProductCard
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.ClearLastSearchConfirmationDialog
import com.kristinakoneva.nutritective.ui.shared.composables.InstructionStep
import com.kristinakoneva.nutritective.ui.shared.models.AllergenStatus
import com.kristinakoneva.nutritective.ui.shared.models.InstructionSteps
import com.kristinakoneva.nutritective.ui.shared.accessibility.screenHeading
import com.kristinakoneva.nutritective.ui.shared.accessibility.sectionHeading
import com.kristinakoneva.nutritective.ui.shared.accessibility.semanticSection
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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

    val scanBarcodeContentDescription = stringResource(R.string.cd_scan_barcode)

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
                text = stringResource(R.string.scan_barcode_screen_title),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.screenHeading(),
            )
            Button(
                modifier = Modifier
                    .padding(top = spacing_3)
                    .fillMaxWidth()
                    .semantics { contentDescription = scanBarcodeContentDescription },
                onClick = onScanBarcodeButtonClicked,
                contentPadding = PaddingValues(spacing_2)
            ) {
                Text(text = stringResource(R.string.scan_barcode).uppercase())
            }
        }

        if (lastSearchedFoodProduct != null) {
            Column(modifier = Modifier.semanticSection(1f)) {
                Text(
                    text = stringResource(R.string.your_last_search),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = spacing_3)
                        .padding(bottom = spacing_2)
                        .sectionHeading(),
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
        }

        if (lastSearchedFoodProduct == null) {
            Column(modifier = Modifier.semanticSection(2f)) {
                Text(
                    modifier = Modifier
                        .padding(top = spacing_3)
                        .fillMaxSize()
                        .sectionHeading(),
                    text = stringResource(R.string.instructions),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(top = spacing_3))
                ScanBarcodeInstructionSteps()
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
fun ScanBarcodeInstructionSteps() {
    val steps = InstructionSteps.getScanBarcodeInstructionSteps()
    Column {
        steps.forEachIndexed { index, step ->
            InstructionStep(
                imageResId = step.imageResId,
                descriptionRes = step.descriptionRes,
                stepIndex = index + 1,
                totalSteps = steps.size + 1,
            )
            Spacer(modifier = Modifier.padding(top = spacing_2))
        }
        Text(
            modifier = Modifier
                .padding(top = spacing_2)
                .sectionHeading(),
            text = stringResource(R.string.alternative),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(top = spacing_3))
        InstructionStep(
            imageResId = R.drawable.illustration_alternative,
            descriptionRes = R.string.scan_barcode_manual_entry_description,
            stepIndex = steps.size + 1,
            totalSteps = steps.size + 1,
        )
    }
}
