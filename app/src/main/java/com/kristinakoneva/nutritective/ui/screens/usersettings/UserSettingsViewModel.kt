package com.kristinakoneva.nutritective.ui.screens.usersettings

import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserSettingsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<UserSettingsState, UserSettingsEvent>(UserSettingsState()) {

    init {
        launchWithLoading {
            viewState = UserSettingsState(
                name = userRepository.getCurrentUser()?.displayName.orEmpty(),
                allergens = userRepository.getUserAllergensList(),
            )
        }
    }

    fun refreshAllergensList() {
        if (viewState.name != null &&
            viewState.showLogoutConfirmationDialog.not() &&
            viewState.showNameChangeDialog.not()
        ) {
            launchWithLoading {
                viewState = viewState.copy(
                    allergens = userRepository.getUserAllergensList(),
                )
            }
        }
    }

    fun onNavigateToSelectAllergens() {
        emitEvent(UserSettingsEvent.NavigateToSelectAllergens)
    }

    fun onNavigateToDetectionInfo() {
        emitEvent(UserSettingsEvent.NavigateToDetectionInfo)
    }

    fun onRemoveAllergenClicked(allergen: String) {
        launch {
            val newAllergenList = viewState.allergens.filter { it != allergen }
            userRepository.setUserAllergensList(newAllergenList)
            viewState = viewState.copy(
                allergens = newAllergenList,
            )
        }
    }

    fun onNavigateBack() {
        emitEvent(UserSettingsEvent.NavigateBack)
    }

    fun onEditButtonClicked() {
        viewState = viewState.copy(
            showNameChangeDialog = true
        )
    }

    fun onNameChangeConfirmed(newName: String) {
        launchWithLoading {
            viewState = viewState.copy(showNameChangeDialog = false)
            userRepository.updateUserDisplayName(newName)
            viewState = viewState.copy(name = newName)
        }
    }

    fun onNameChangeCancelled() {
        viewState = viewState.copy(
            showNameChangeDialog = false
        )
    }

    fun onLogoutButtonClicked() {
        viewState = viewState.copy(
            showLogoutConfirmationDialog = true
        )
    }

    fun onLogoutCancelled() {
        viewState = viewState.copy(
            showLogoutConfirmationDialog = false
        )
    }

    fun onLogoutConfirmed() {
        launch {
            viewState = viewState.copy(showLogoutConfirmationDialog = false)
            userRepository.logoutUser()
            emitEvent(UserSettingsEvent.NavigateToAuth)
        }
    }
}
