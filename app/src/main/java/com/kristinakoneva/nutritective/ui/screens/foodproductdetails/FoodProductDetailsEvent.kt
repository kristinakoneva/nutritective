package com.kristinakoneva.nutritective.ui.screens.foodproductdetails

sealed interface FoodProductDetailsEvent {

    data object OnNavigateToScanBarcode : FoodProductDetailsEvent
}
