package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.theme.NutritectiveDialogDefaults

@Composable
fun ClearLastSearchConfirmationDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        containerColor = NutritectiveDialogDefaults.containerColor,
        titleContentColor = NutritectiveDialogDefaults.titleContentColor,
        textContentColor = NutritectiveDialogDefaults.textContentColor,
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = stringResource(R.string.clear))
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text(text = stringResource(R.string.cancel))
            }
        },
        text = { Text(stringResource(R.string.clear_last_search_confirmation_message)) },
        title = { Text(stringResource(R.string.clear_last_search)) }
    )
}
