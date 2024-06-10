package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ClearLastSearchConfirmationDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = "Clear")
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text(text = "Cancel")
            }
        },
        text = { Text("Are you sure you want to clear your last search?") },
        title = { Text("Clear last search") }
    )
}
