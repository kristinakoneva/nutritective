package com.kristinakoneva.nutritective.ui.screens.usersettings

sealed interface UserSettingsEvent {
    data object NavigateToSelectAllergens : UserSettingsEvent
}
