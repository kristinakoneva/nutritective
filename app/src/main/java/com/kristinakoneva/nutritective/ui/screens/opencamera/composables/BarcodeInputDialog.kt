package com.kristinakoneva.nutritective.ui.screens.opencamera.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.kristinakoneva.nutritective.extensions.isDigitsOnly
import com.kristinakoneva.nutritective.ui.theme.spacing_1

@Composable
fun BarcodeInputDialog(
    onConfirm: (String) -> Unit,
    onCancel: () -> Unit
) {
    var barcode by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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
                    label = { Text("Barcode") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = errorMessage.isNotBlank(),
                    modifier = Modifier.padding(bottom = spacing_1)
                )

                if (errorMessage.isNotBlank()) {
                    Text(errorMessage, color = Color.Red)
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
