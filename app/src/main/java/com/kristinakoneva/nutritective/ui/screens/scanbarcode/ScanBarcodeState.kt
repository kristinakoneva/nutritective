package com.kristinakoneva.nutritective.ui.screens.scanbarcode

import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct

data class ScanBarcodeState(
    val product: FoodProduct? = null
)
