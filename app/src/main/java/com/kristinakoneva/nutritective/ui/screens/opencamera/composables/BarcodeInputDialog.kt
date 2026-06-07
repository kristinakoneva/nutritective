package com.kristinakoneva.nutritective.ui.screens.opencamera.composables

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.extensions.isDigitsOnly
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_error
import com.kristinakoneva.nutritective.ui.theme.spacing_1

@Composable
fun BarcodeInputDialog(
    onConfirm: (String) -> Unit,
    onCancel: () -> Unit
) {
    var barcode by remember { mutableStateOf("") }
    var showInvalidBarcodeError by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(text = stringResource(R.string.enter_product_barcode_title)) },
        text = {
            Column {
                OutlinedTextField(
                    value = barcode,
                    onValueChange = {
                        barcode = it
                        showInvalidBarcodeError = false
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
                    label = { Text(stringResource(R.string.barcode)) },
                    isError = showInvalidBarcodeError,
                    modifier = Modifier.padding(bottom = spacing_1)
                )

                if (showInvalidBarcodeError) {
                    Text(stringResource(R.string.invalid_barcode_error), color = md_theme_dark_error)
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (barcode.isNotBlank() && barcode.isDigitsOnly()) {
                    onConfirm(barcode)
                } else {
                    showInvalidBarcodeError = true
                }
            }) {
                Text(stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}
