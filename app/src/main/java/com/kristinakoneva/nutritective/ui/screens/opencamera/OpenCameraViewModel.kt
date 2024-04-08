package com.kristinakoneva.nutritective.ui.screens.opencamera

import com.kristinakoneva.nutritective.domain.foodproducts.FoodProductsRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OpenCameraViewModel @Inject constructor(
    private val foodProductsRepository: FoodProductsRepository
) : BaseViewModel<Unit, Unit>(Unit) {
    fun onBarcodeScanned(barcode: String?) {
        launch {
            if (barcode == null) {
                return@launch
            }
            val product = foodProductsRepository.fetchFoodProductByBarcode(barcode)
        }
    }
}
