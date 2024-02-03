package com.kristinakoneva.nutritective.ui.shared.base

sealed interface LoadingState {
    class Idle : LoadingState
    class Loading : LoadingState
}