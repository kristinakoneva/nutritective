package com.kristinakoneva.nutritective.ui.shared.models

import androidx.annotation.StringRes

data class InstructionStep(
    @StringRes val descriptionRes: Int,
    val imageResId: Int
)
