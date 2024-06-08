package com.kristinakoneva.nutritective.ui.screens.selectallergens

import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectAllergensViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<SelectAllergensState, SelectAllergensEvent>(SelectAllergensState()) {

    private var inputText: String = ""
    private var initialSelections = emptyList<String>()

    init {
        launchWithLoading {
            initialSelections = userRepository.getUserAllergensList()
            viewState = viewState.copy(
                selectedAllergens = initialSelections,
                manuallyAddedAllergens = initialSelections.filterNot {
                    viewState.commonAllergens.map { allergen -> allergen.text }.contains(it)
                }
            )
        }
    }

    fun onAllergenSelected(allergen: String) {
        val selectedAllergens = viewState.selectedAllergens.toMutableList().apply {
            if (contains(allergen)) {
                remove(allergen)
            } else {
                add(allergen)
            }
        }
        viewState = viewState.copy(
            selectedAllergens = selectedAllergens,
            isSaveChangesButtonEnabled = selectedAllergens != initialSelections
        )
    }

    fun onInputTextChanged(inputText: String) {
        this.inputText = inputText
        viewState = viewState.copy(inputText = inputText)
    }

    fun onAddAllergenClicked() {
        val allergenName = inputText.trim()
        inputText = ""

        if (allergenName.isEmpty()) return
        if (viewState.commonAllergens.map { it.text }.contains(allergenName)) {
            onAllergenSelected(allergenName)
            viewState = viewState.copy(inputText = "")
            return
        }

        val selectedAllergens = viewState.selectedAllergens.toMutableList().apply {
            add(allergenName)
        }
        val manuallyAddedAllergens = if(viewState.manuallyAddedAllergens.contains(allergenName)) {
            viewState.manuallyAddedAllergens
        } else {
            viewState.manuallyAddedAllergens.toMutableList().apply {
                add(allergenName)
            }
        }
        viewState = viewState.copy(
            manuallyAddedAllergens = manuallyAddedAllergens,
            selectedAllergens = selectedAllergens,
            inputText = "",
            isSaveChangesButtonEnabled = initialSelections != selectedAllergens
        )
    }

    fun onSaveChangesButtonClicked() {
        launchWithLoading {
            userRepository.setUserAllergensList(viewState.selectedAllergens)
            initialSelections = viewState.selectedAllergens
            emitEvent(SelectAllergensEvent.NavigateToUserSettings)
        }
    }
}
