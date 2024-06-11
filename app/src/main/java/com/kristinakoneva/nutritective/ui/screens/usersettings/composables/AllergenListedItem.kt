package com.kristinakoneva.nutritective.ui.screens.usersettings.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kristinakoneva.nutritective.ui.theme.medium_icon_size

@Composable
fun AllergenListedItem(
    name: String,
    onClick: () -> Unit,
    onRemoveAllergen: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = name, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1F))
        IconButton(onClick = onRemoveAllergen) {
            Icon(
                imageVector = Icons.Default.DeleteOutline,
                contentDescription = "Delete icon",
                modifier = Modifier.size(medium_icon_size)
            )
        }
        OutlinedButton(onClick = onClick) {
            Text(text = "Avoid products")
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Arrow forward"
            )
        }
    }
}
