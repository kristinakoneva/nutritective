package com.kristinakoneva.nutritective.ui.screens.selectallergens

import com.kristinakoneva.nutritective.ui.shared.utils.CommonAllergen

data class SelectAllergensState(
    val commonAllergens: List<CommonAllergen> = CommonAllergen.entries,
    val manuallyAddedAllergens: List<String> = emptyList(),
    val selectedAllergens: List<String> = emptyList(),
    val inputText: String? = null,
    val isSaveChangesButtonEnabled: Boolean = false
)
