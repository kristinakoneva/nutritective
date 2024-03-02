package com.kristinakoneva.nutritective.ui.shared.base

sealed interface ErrorEvent {

    class UnknownError : ErrorEvent
}