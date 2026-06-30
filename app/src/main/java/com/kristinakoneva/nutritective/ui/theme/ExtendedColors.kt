package com.kristinakoneva.nutritective.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val allergenSafe: Color,
    val allergenWarning: Color,
    val allergenDanger: Color,
)

val LocalExtendedColors = staticCompositionLocalOf<ExtendedColors> {
    error("ExtendedColors not provided. Wrap content in NutritectiveTheme.")
}

val MaterialTheme.extendedColors: ExtendedColors
    @Composable
    @ReadOnlyComposable
    get() = LocalExtendedColors.current
