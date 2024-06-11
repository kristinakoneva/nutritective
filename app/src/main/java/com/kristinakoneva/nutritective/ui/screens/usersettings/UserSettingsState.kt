package com.kristinakoneva.nutritective.ui.screens.usersettings

data class UserSettingsState(
    val name: String? = null,
    val allergens: List<String> = emptyList(),
    val showLogoutConfirmationDialog: Boolean = false,
    val showNameChangeDialog: Boolean = false
)
