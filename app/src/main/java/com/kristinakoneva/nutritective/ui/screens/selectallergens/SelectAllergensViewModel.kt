package com.kristinakoneva.nutritective.ui.screens.selectallergens

import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectAllergensViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<SelectAllergensState, Unit>(SelectAllergensState()) {

    init {
        launchWithLoading {
            viewState = viewState.copy(
                selectedAllergens = userRepository.getUserAllergensList(),
            )
        }
    }

    fun onCommonAllergenSelected(allergen: String) {
        viewState = viewState.copy(
            selectedAllergens = viewState.selectedAllergens.toMutableList().apply {
                if (contains(allergen)) {
                    remove(allergen)
                } else {
                    add(allergen)
                }
            }
        )
    }
}
