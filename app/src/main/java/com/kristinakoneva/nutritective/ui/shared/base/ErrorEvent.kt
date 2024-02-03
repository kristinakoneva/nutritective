package com.kristinakoneva.nutritective.ui.shared.base

sealed class ErrorEvent {

    class UnknownError : ErrorEvent()
}