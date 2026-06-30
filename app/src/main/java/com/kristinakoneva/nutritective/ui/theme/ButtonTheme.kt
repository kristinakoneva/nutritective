package com.kristinakoneva.nutritective.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun nutritectiveButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
    disabledContainerColor = MaterialTheme.colorScheme.secondary,
    disabledContentColor = MaterialTheme.colorScheme.onSecondary,
)
