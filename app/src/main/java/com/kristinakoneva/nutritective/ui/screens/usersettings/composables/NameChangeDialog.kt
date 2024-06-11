package com.kristinakoneva.nutritective.ui.screens.usersettings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_error
import com.kristinakoneva.nutritective.ui.theme.spacing_1

@Composable
fun NameChangeDialog(
    currentName: String,
    onConfirm: (String) -> Unit,
    onCancel: () -> Unit
) {
    var newName by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(text = "Enter new name") },
        text = {
            Column {
                OutlinedTextField(
                    value = newName,
                    onValueChange = {
                        newName = it
                        errorMessage = ""
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onConfirm(newName)
                        }
                    ),
                    label = { Text("New name") },
                    isError = errorMessage.isNotBlank() || newName == currentName,
                    modifier = Modifier.padding(bottom = spacing_1)
                )

                if (errorMessage.isNotBlank()) {
                    Text(errorMessage, color = md_theme_dark_error)
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (newName.isNotBlank() && newName != currentName) {
                    onConfirm(newName)
                } else {
                    errorMessage = "New name cannot be empty or same as the current one!"
                }
            }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}
