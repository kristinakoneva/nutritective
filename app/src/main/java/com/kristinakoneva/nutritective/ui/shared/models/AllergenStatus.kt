package com.kristinakoneva.nutritective.ui.shared.models

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.kristinakoneva.nutritective.R

enum class AllergenStatus(
    @StringRes val labelRes: Int,
    val color: Color,
    val iconResId: Int
) {
    SAFE(
        R.string.allergen_status_safe,
        Color(0xFF4CAF50),
        R.drawable.ic_safe
    ),
    WARNING(
        R.string.allergen_status_warning,
        Color(0xFFFFC107),
        R.drawable.ic_warning
    ),
    DANGER(
        R.string.allergen_status_danger,
        Color(0xFFF44336),
        R.drawable.ic_danger
    )
}
