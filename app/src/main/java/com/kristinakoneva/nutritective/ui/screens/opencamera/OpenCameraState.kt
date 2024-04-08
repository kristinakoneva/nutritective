package com.kristinakoneva.nutritective.ui.screens.opencamera

sealed interface OpenCameraState {
    data object Initial : OpenCameraState
    data object ProductNotFound : OpenCameraState
}
