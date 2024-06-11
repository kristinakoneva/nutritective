package com.kristinakoneva.nutritective.ui.screens.opencamera

sealed interface OpenCameraEvent {

    data object ProductFound : OpenCameraEvent

    data object NavigateBack : OpenCameraEvent
}
