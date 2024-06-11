package com.kristinakoneva.nutritective.ui.screens.detectioninfo

import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetectionInfoViewModel @Inject constructor() : BaseViewModel<Unit, DetectionInfoEvent>(Unit) {
    fun onNavigateBack() {
        emitEvent(DetectionInfoEvent.NavigateBack)
    }
}
