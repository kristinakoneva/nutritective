package com.kristinakoneva.nutritective.ui.screens.scanbarcode

import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models.ProductResource

data class ScanBarcodeState(
    val product: ProductResource? = null,
)
