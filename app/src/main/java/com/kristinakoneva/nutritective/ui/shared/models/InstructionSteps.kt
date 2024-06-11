package com.kristinakoneva.nutritective.ui.shared.models

import com.kristinakoneva.nutritective.R

object InstructionSteps {
    fun getScanBarcodeInstructionSteps(): List<InstructionStep> = listOf(
        InstructionStep(
            description = "Get a food product whose nutrition data interests you.",
            imageResId = R.drawable.illustration_food_products
        ),
        InstructionStep(
            description = "Locate the barcode on the food product and point your camera at it.",
            imageResId = R.drawable.illustration_locate_barcode
        ),
        InstructionStep(
            description = "View the available nutrition information about the food product.",
            imageResId = R.drawable.illustration_nutrition_data
        )
    )

    fun getInspectImageInstructionSteps(): List<InstructionStep> = listOf(
        InstructionStep(
            description = "Take a photo or choose one from gallery which contains food related words.\nFor example, take a photo of a restaurant menu.",
            imageResId = R.drawable.illustration_menu
        ),
        InstructionStep(
            description = "View the available nutrition information about the food or drink items detected from the photo.",
            imageResId = R.drawable.illustration_nutrition_data
        )
    )

    fun getAnalyzeTextInstructionSteps(): List<InstructionStep> = listOf(
        InstructionStep(
            description = "Enter some text containing food or drink items.\nFor example enter what you had for lunch: \"steak and fries.\"",
            imageResId = R.drawable.illustration_text_input
        ),
        InstructionStep(
            description = "If you want to get a more precise nutrition data, you can prefix the item with a quantity.\nExample: \"20g chocolate and 2 bananas\"\n(the default quantity is 100 grams)",
            imageResId = R.drawable.illustration_weight
        ),
        InstructionStep(
            description = "View the available nutrition information about the food or drink items detected from the input text.",
            imageResId = R.drawable.illustration_nutrition_data
        )
    )

    fun getExploreRecipesInstructionSteps(): List<InstructionStep> = listOf(
        InstructionStep(
            description = "Enter the name of a dish you want to cook or eat.",
            imageResId = R.drawable.illustration_think_food
        ),
        InstructionStep(
            description = "View the available recipes for the dish you entered.",
            imageResId = R.drawable.illustration_recipe
        )
    )
}
