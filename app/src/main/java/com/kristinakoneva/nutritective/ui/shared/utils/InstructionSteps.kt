package com.kristinakoneva.nutritective.ui.shared.utils

import com.kristinakoneva.nutritective.R

object InstructionSteps {
    fun getScanBarcodeInstructionSteps(): List<InstructionStep> = listOf(
        InstructionStep(
            description = "Step 1: Get a food product whose nutrition data interests you.",
            imageResId = R.drawable.illustration_food_products
        ),
        InstructionStep(
            description = "Step 2: Locate the barcode on the food product and point your camera at it.",
            imageResId = R.drawable.illustration_locate_barcode
        ),
        InstructionStep(
            description = "Step 3: View the available nutrition information about the food product.",
            imageResId = R.drawable.illustration_nutrition_data
        )
    )
}
