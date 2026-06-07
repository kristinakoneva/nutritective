package com.kristinakoneva.nutritective.ui.shared.models

import com.kristinakoneva.nutritective.R

object InstructionSteps {
    fun getScanBarcodeInstructionSteps(): List<InstructionStep> = listOf(
        InstructionStep(
            descriptionRes = R.string.instruction_scan_barcode_step_1,
            imageResId = R.drawable.illustration_food_products
        ),
        InstructionStep(
            descriptionRes = R.string.instruction_scan_barcode_step_2,
            imageResId = R.drawable.illustration_locate_barcode
        ),
        InstructionStep(
            descriptionRes = R.string.instruction_scan_barcode_step_3,
            imageResId = R.drawable.illustration_nutrition_data
        )
    )

    fun getInspectImageInstructionSteps(): List<InstructionStep> = listOf(
        InstructionStep(
            descriptionRes = R.string.instruction_inspect_image_step_1,
            imageResId = R.drawable.illustration_menu
        ),
        InstructionStep(
            descriptionRes = R.string.instruction_inspect_image_step_2,
            imageResId = R.drawable.illustration_nutrition_data
        )
    )

    fun getAnalyzeTextInstructionSteps(): List<InstructionStep> = listOf(
        InstructionStep(
            descriptionRes = R.string.instruction_analyze_text_step_1,
            imageResId = R.drawable.illustration_text_input
        ),
        InstructionStep(
            descriptionRes = R.string.instruction_analyze_text_step_2,
            imageResId = R.drawable.illustration_weight
        ),
        InstructionStep(
            descriptionRes = R.string.instruction_analyze_text_step_3,
            imageResId = R.drawable.illustration_nutrition_data
        )
    )

    fun getExploreRecipesInstructionSteps(): List<InstructionStep> = listOf(
        InstructionStep(
            descriptionRes = R.string.instruction_explore_recipes_step_1,
            imageResId = R.drawable.illustration_think_food
        ),
        InstructionStep(
            descriptionRes = R.string.instruction_explore_recipes_step_2,
            imageResId = R.drawable.illustration_recipe
        )
    )
}
