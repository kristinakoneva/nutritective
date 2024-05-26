package com.kristinakoneva.nutritective.ui.screens.usersettings

import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserSettingsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<UserSettingsState, UserSettingsEvent>(UserSettingsState.Initial) {

    init {
        launchWithLoading {
            viewState = UserSettingsState.Content(
                name = userRepository.getCurrentUser()?.displayName.orEmpty(),
                allergens = userRepository.getUserAllergensList(),
            )
        }
    }

    fun onNavigateToSelectAllergens() {
        emitEvent(UserSettingsEvent.NavigateToSelectAllergens)
    }
}
