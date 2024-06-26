package com.kristinakoneva.nutritective.ui.screens.scanbarcode

import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import com.kristinakoneva.nutritective.ui.shared.models.AllergenStatus

data class ScanBarcodeState(
    val lastSearch: FoodProduct? = null,
    val allergenStatus: AllergenStatus? = null,
    val detectedAllergens: List<String>? = null,
    val showClearLastSearchDialog: Boolean = false,
)
