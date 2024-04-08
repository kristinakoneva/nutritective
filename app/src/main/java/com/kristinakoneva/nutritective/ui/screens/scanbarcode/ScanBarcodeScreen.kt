package com.kristinakoneva.nutritective.ui.screens.scanbarcode

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen

@Composable
fun ScanBarcodeScreen(
    viewModel: ScanBarcodeViewModel = hiltViewModel(),
    onNavigateToOpenCamera: () -> Unit
) {
    BaseScreen(viewModel = viewModel, eventHandler = {
        when (it) {
            ScanBarcodeEvent.NavigateToOpenCamera -> onNavigateToOpenCamera()
        }
    }) {
        ScanBarcodeScreenContent(
            onScanBarcodeButtonClicked = viewModel::onScanBarcodeButtonClicked
        )
    }
}

@Composable
fun ScanBarcodeScreenContent(
    onScanBarcodeButtonClicked: () -> Unit
) {
    Button(onClick = { onScanBarcodeButtonClicked() }) {
        Text(text = "Scan barcode")
    }
}
