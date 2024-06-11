package com.kristinakoneva.nutritective.ui.screens.selectallergens

sealed interface SelectAllergensEvent {
    data object NavigateToUserSettings : SelectAllergensEvent

    data object NavigateBack : SelectAllergensEvent
}
