package com.kristinakoneva.nutritective.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object NutritectiveDialogDefaults {
    val containerColor: Color
        @Composable get() = MaterialTheme.colorScheme.background

    val titleContentColor: Color
        @Composable get() = md_theme_dark_onSecondary

    val textContentColor: Color
        @Composable get() = md_theme_dark_onSecondary
}
