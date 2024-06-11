package com.kristinakoneva.nutritective.ui.screens.foodproductdetails

import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import com.kristinakoneva.nutritective.ui.shared.models.AllergenStatus

sealed interface FoodProductDetailsState {

    data object Initial : FoodProductDetailsState

    data class Content(
        val foodProduct: FoodProduct,
        val allergenStatus: AllergenStatus? = null,
        val detectedAllergens: List<String>? = null
    ) : FoodProductDetailsState
}
