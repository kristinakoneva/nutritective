package com.kristinakoneva.nutritective.ui.screens.selectallergens

import com.kristinakoneva.nutritective.ui.shared.utils.CommonAllergen

data class SelectAllergensState(
    val commonAllergens: List<CommonAllergen> = CommonAllergen.entries,
    val customAllergens: List<String> = emptyList(),
    val selectedCommonAllergens: List<CommonAllergen> = emptyList(),
    val selectedCustomAllergens: List<String> = emptyList()
)
