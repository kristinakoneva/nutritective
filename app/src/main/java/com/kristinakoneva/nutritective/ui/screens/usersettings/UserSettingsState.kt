package com.kristinakoneva.nutritective.ui.screens.usersettings

sealed interface UserSettingsState {

    data object Initial : UserSettingsState

    data class Content(
        val name: String
    )
}
