package com.kristinakoneva.nutritective.ui.screens.usersettings

import android.util.Log
import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserSettingsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<UserSettingsState, Unit>(UserSettingsState.Initial) {

    init {
        launchWithLoading {
            viewState = UserSettingsState.Content(
                name = userRepository.getCurrentUser()?.displayName.orEmpty(),
                allergens = userRepository.getUserAllergensList(),
            )
        }
    }
}
