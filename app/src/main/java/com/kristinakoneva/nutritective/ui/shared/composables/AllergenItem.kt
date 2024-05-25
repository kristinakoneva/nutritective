package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AllergenListedItem(
    name: String,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = name, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1F))
        OutlinedButton(onClick = onClick) {
            Text(text = "Avoid products")
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Arrow forward")
        }
    }
}
