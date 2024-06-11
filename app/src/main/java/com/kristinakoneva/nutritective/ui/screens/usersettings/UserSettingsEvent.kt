package com.kristinakoneva.nutritective.ui.screens.usersettings

sealed interface UserSettingsEvent {
    data object NavigateToSelectAllergens : UserSettingsEvent

    data object NavigateToDetectionInfo : UserSettingsEvent

    data object NavigateBack : UserSettingsEvent

    data object NavigateToAuth : UserSettingsEvent
}
