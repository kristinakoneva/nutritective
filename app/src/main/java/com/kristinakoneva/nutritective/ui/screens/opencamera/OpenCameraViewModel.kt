package com.kristinakoneva.nutritective.ui.screens.opencamera

import com.kristinakoneva.nutritective.domain.foodproducts.FoodProductsRepository
import com.kristinakoneva.nutritective.domain.session.SessionRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OpenCameraViewModel @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val foodProductsRepository: FoodProductsRepository
) : BaseViewModel<OpenCameraState, OpenCameraEvent>(OpenCameraState.Initial) {
    fun onBarcodeScanned(barcode: String) {
        launch {
            try {
                val product = foodProductsRepository.fetchFoodProductByBarcode(barcode)
                if (product == null) {
                    viewState = OpenCameraState.ProductNotFound
                } else {
                    sessionRepository.saveLastScannedFoodProductBarcode(barcode)
                    emitEvent(OpenCameraEvent.ProductFound)
                }
            } catch (e: Exception) {
                viewState = OpenCameraState.ProductNotFound
            }
        }
    }

    fun onTryAnotherProductButtonClicked() {
        viewState = OpenCameraState.Initial
    }

    fun onEnterBarcodeManuallyButtonClicked() {
        viewState = OpenCameraState.ShowBarcodeInputDialog
    }

    fun resetToInitialState() {
        viewState = OpenCameraState.Initial
    }

    fun onNavigateBack() {
        emitEvent(OpenCameraEvent.NavigateBack)
    }
}
