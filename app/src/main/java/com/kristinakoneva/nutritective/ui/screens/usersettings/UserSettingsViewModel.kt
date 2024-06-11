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
        if (viewState.name != null) {
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
        launchWithLoading {
            val newAllergenList = viewState.allergens.filter { it != allergen }
            userRepository.setUserAllergensList(newAllergenList)
            viewState = viewState.copy(
                allergens = newAllergenList,
            )
        }
    }
}
