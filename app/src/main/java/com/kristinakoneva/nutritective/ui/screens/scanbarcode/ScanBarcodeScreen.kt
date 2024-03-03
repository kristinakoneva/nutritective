package com.kristinakoneva.nutritective.ui.screens.scanbarcode

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen

@Composable
fun ScanBarcodeScreen(
    viewModel: ScanBarcodeViewModel = hiltViewModel(),
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) {
        Column {
            Text(text = "Scan Barcode Screen")
        }
    }
}