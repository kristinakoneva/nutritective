package com.kristinakoneva.nutritective.ui.screens.opencamera.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.kristinakoneva.nutritective.extensions.isDigitsOnly
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_error
import com.kristinakoneva.nutritective.ui.theme.spacing_1

@Composable
fun BarcodeInputDialog(
    onConfirm: (String) -> Unit,
    onCancel: () -> Unit
) {
    var barcode by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(text = "Enter Product Barcode") },
        text = {
            Column {
                OutlinedTextField(
                    value = barcode,
                    onValueChange = {
                        barcode = it
                        errorMessage = ""
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onConfirm(barcode)
                        }
                    ),
                    label = { Text("Barcode") },
                    isError = errorMessage.isNotBlank(),
                    modifier = Modifier.padding(bottom = spacing_1)
                )

                if (errorMessage.isNotBlank()) {
                    Text(errorMessage, color = md_theme_dark_error)
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (barcode.isNotBlank() && barcode.isDigitsOnly()) {
                    onConfirm(barcode)
                } else {
                    errorMessage = "Invalid barcode!"
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
