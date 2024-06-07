package com.kristinakoneva.nutritective.ui.screens.usersettings

data class UserSettingsState(
    val name: String = "",
    val allergens: List<String> = emptyList(),
)
