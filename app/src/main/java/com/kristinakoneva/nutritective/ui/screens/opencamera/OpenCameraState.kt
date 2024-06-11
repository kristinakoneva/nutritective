package com.kristinakoneva.nutritective.ui.screens.opencamera

sealed interface OpenCameraState {
    data class Initial(val showBarcodeInputDialog: Boolean = false) : OpenCameraState
    data object ProductNotFound : OpenCameraState
}
