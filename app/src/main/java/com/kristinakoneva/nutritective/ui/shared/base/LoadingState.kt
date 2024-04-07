package com.kristinakoneva.nutritective.ui.shared.base

sealed interface LoadingState {
    data object Idle : LoadingState
    data object Loading : LoadingState
}
