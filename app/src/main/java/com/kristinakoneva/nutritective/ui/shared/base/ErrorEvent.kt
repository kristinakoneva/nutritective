package com.kristinakoneva.nutritective.ui.shared.base

sealed interface ErrorEvent {

    data object UnknownError : ErrorEvent
}
