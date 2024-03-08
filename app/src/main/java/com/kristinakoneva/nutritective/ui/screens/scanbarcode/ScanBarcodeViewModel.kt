package com.kristinakoneva.nutritective.ui.screens.scanbarcode

import com.kristinakoneva.nutritective.data.remote.api.openfoodfacts.OpenFoodFactsSource
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanBarcodeViewModel @Inject constructor(
    private val source: OpenFoodFactsSource
) : BaseViewModel<ScanBarcodeState, Unit>(ScanBarcodeState()) {

    fun onBarcodeScanned(barcode: String?) {
        launch {
            if (barcode == null) {
                viewState = ScanBarcodeState(null)
                return@launch
            }
            val name = source.getProductNutritionData(barcode).product.productName
            viewState = ScanBarcodeState(name)
        }
    }
}