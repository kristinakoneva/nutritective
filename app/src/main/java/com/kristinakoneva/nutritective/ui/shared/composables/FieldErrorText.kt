package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_error

/** Error shown as the field's supporting text, so TalkBack reads it together with the field (WCAG 3.3.1). */
fun errorText(show: Boolean, @StringRes message: Int): (@Composable () -> Unit)? =
    if (show) {
        { Text(stringResource(message), color = md_theme_dark_error) }
    } else {
        null
    }
