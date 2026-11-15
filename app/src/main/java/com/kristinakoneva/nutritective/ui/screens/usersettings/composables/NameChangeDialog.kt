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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.shared.accessibility.moveFocusOnTab
import com.kristinakoneva.nutritective.ui.shared.composables.errorText
import com.kristinakoneva.nutritective.ui.theme.NutritectiveDialogDefaults
import com.kristinakoneva.nutritective.ui.theme.nutritectiveOutlinedTextFieldColors
import com.kristinakoneva.nutritective.ui.theme.spacing_1

@Composable
fun NameChangeDialog(
    currentName: String,
    onConfirm: (String) -> Unit,
    onCancel: () -> Unit
) {
    var newName by remember { mutableStateOf("") }
    var showInvalidNameError by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    fun submitName() {
        if (newName.isNotBlank() && newName != currentName) {
            onConfirm(newName)
        } else {
            showInvalidNameError = true
        }
    }

    AlertDialog(
        onDismissRequest = onCancel,
        containerColor = NutritectiveDialogDefaults.containerColor,
        titleContentColor = NutritectiveDialogDefaults.titleContentColor,
        textContentColor = NutritectiveDialogDefaults.textContentColor,
        title = { Text(text = stringResource(R.string.enter_new_name_title)) },
        text = {
            Column {
                OutlinedTextField(
                    value = newName,
                    textStyle = TextStyle(color = Color.Black),
                    onValueChange = {
                        newName = it
                        showInvalidNameError = false
                    },
                    colors = nutritectiveOutlinedTextFieldColors(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            submitName()
                        }
                    ),
                    label = { Text(stringResource(R.string.new_name_label)) },
                    isError = showInvalidNameError || newName == currentName,
                    supportingText = errorText(showInvalidNameError, R.string.new_name_invalid_error),
                    singleLine = true,
                    modifier = Modifier
                        .padding(bottom = spacing_1)
                        .moveFocusOnTab(focusManager)
                )
            }
        },
        confirmButton = {
            Button(onClick = ::submitName) {
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
