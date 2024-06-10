package com.kristinakoneva.nutritective.ui.shared.utils

import androidx.compose.ui.graphics.Color
import com.kristinakoneva.nutritective.R

enum class AllergenStatus(
    val text: String,
    val color: Color,
    val iconResId: Int
) {
    SAFE(
        "Safe to consume",
        Color(0xFF4CAF50),
        R.drawable.ic_safe
    ),
    WARNING(
        "May contain allergens",
        Color(0xFFFFC107),
        R.drawable.ic_warning
    ),
    DANGER(
        "Dangerous allergens present",
        Color(0xFFF44336),
        R.drawable.ic_danger
    )
}
