package com.kristinakoneva.nutritective.ui.shared.models

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.theme.allergen_danger
import com.kristinakoneva.nutritective.ui.theme.allergen_safe
import com.kristinakoneva.nutritective.ui.theme.allergen_warning

enum class AllergenStatus(
    @StringRes val labelRes: Int,
    val color: Color,
    val iconResId: Int
) {
    SAFE(
        R.string.allergen_status_safe,
        allergen_safe,
        R.drawable.ic_safe
    ),
    WARNING(
        R.string.allergen_status_warning,
        allergen_warning,
        R.drawable.ic_warning
    ),
    DANGER(
        R.string.allergen_status_danger,
        allergen_danger,
        R.drawable.ic_danger
    )
}
