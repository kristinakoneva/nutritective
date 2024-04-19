package com.kristinakoneva.nutritective.ui.screens.inspectimage

import android.net.Uri
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem

data class InspectImageState(
    val uri: Uri? = null,
    val foodItems: List<FoodItem>? = null,
    val selectedFoodItem: FoodItem? = null
)
