package com.kristinakoneva.nutritective.ui.screens.foodproductdetails

import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct

sealed interface FoodProductDetailsState {

    data object Initial : FoodProductDetailsState

    data class Content(val foodProduct: FoodProduct) : FoodProductDetailsState
}
