package com.kristinakoneva.nutritective.ui.screens.scanbarcode

import com.kristinakoneva.nutritective.domain.foodproducts.FoodProductsRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanBarcodeViewModel @Inject constructor(
    private val foodProductsRepository: FoodProductsRepository
) : BaseViewModel<ScanBarcodeState, Unit>(ScanBarcodeState()) {

    fun onBarcodeScanned(barcode: String?) {
        launch {
            if (barcode == null) {
                viewState = ScanBarcodeState(null)
                return@launch
            }
            val product = foodProductsRepository.fetchFoodProductByBarcode(barcode)
            viewState = ScanBarcodeState(product)
        }
    }
}
