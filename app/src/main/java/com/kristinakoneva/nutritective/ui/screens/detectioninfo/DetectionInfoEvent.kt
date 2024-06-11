package com.kristinakoneva.nutritective.ui.screens.detectioninfo

sealed interface DetectionInfoEvent {
    data object NavigateBack : DetectionInfoEvent
}
