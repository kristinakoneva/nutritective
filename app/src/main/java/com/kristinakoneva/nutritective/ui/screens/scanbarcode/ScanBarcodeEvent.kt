package com.kristinakoneva.nutritective.ui.screens.scanbarcode

sealed interface ScanBarcodeEvent {

    data object NavigateToOpenCamera : ScanBarcodeEvent

    data object NavigateToFoodProductDetails : ScanBarcodeEvent
}
