package com.kristinakoneva.nutritective.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable

@Composable
fun nutritectiveOutlinedTextFieldColors(): TextFieldColors = OutlinedTextFieldDefaults.colors(
    focusedTextColor = md_theme_dark_onSecondary,
    unfocusedTextColor = md_theme_dark_onSecondary,
    disabledTextColor = input_hint,
    unfocusedLabelColor = input_hint,
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    disabledLabelColor = input_hint,
    unfocusedPlaceholderColor = input_hint,
    focusedPlaceholderColor = input_hint,
    disabledPlaceholderColor = input_hint,
)
