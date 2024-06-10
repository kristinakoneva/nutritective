package com.kristinakoneva.nutritective.ui.screens.inspectimage

import android.net.Uri
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus

data class InspectImageState(
    val uri: Uri? = null,
    val foodItems: List<FoodItem>? = null,
    val selectedFoodItem: FoodItem? = null,
    val allergenStatus: AllergenStatus? = null,
    val detectedAllergens: List<String>? = null,
    val showClearLastSearchDialog: Boolean = false
)
