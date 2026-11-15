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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.extensions.isDigitsOnly
import com.kristinakoneva.nutritective.ui.shared.accessibility.moveFocusOnTab
import com.kristinakoneva.nutritective.ui.shared.composables.errorText
import com.kristinakoneva.nutritective.ui.theme.NutritectiveDialogDefaults
import com.kristinakoneva.nutritective.ui.theme.nutritectiveOutlinedTextFieldColors
import com.kristinakoneva.nutritective.ui.theme.spacing_1

@Composable
fun BarcodeInputDialog(
    onConfirm: (String) -> Unit,
    onCancel: () -> Unit
) {
    var barcode by remember { mutableStateOf("") }
    var showInvalidBarcodeError by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    fun submitBarcode() {
        if (barcode.isNotBlank() && barcode.isDigitsOnly()) {
            onConfirm(barcode)
        } else {
            showInvalidBarcodeError = true
        }
    }

    AlertDialog(
        onDismissRequest = onCancel,
        containerColor = NutritectiveDialogDefaults.containerColor,
        titleContentColor = NutritectiveDialogDefaults.titleContentColor,
        textContentColor = NutritectiveDialogDefaults.textContentColor,
        title = { Text(text = stringResource(R.string.enter_product_barcode_title)) },
        text = {
            Column {
                OutlinedTextField(
                    value = barcode,
                    textStyle = TextStyle(color = Color.Black),
                    onValueChange = {
                        barcode = it
                        showInvalidBarcodeError = false
                    },
                    colors = nutritectiveOutlinedTextFieldColors(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            submitBarcode()
                        }
                    ),
                    label = { Text(stringResource(R.string.barcode)) },
                    isError = showInvalidBarcodeError,
                    supportingText = errorText(showInvalidBarcodeError, R.string.invalid_barcode_error),
                    singleLine = true,
                    modifier = Modifier
                        .padding(bottom = spacing_1)
                        .moveFocusOnTab(focusManager)
                )
            }
        },
        confirmButton = {
            Button(onClick = ::submitBarcode) {
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
