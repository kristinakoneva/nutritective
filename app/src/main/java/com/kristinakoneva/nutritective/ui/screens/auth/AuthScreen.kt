package com.kristinakoneva.nutritective.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_error
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onNavigateToScanBarcodeScreen: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    BaseScreen(viewModel = viewModel, eventHandler = {
        when (it) {
            is AuthEvent.SuccessfulAuth -> onNavigateToScanBarcodeScreen()
            is AuthEvent.FailedAuth -> {
                focusManager.clearFocus()
                Toast.makeText(context, "Failed to authenticate", Toast.LENGTH_SHORT).show()
            }
        }
    }) { state ->
        AuthScreenContent(
            name = state.name,
            email = state.email,
            password = state.password,
            confirmPassword = state.confirmPassword,
            onNameInputFieldValueChange = viewModel::onNameInputFieldValueChanged,
            onEmailInputFieldValueChange = viewModel::onEmailInputFieldValueChanged,
            onPasswordInputFieldValueChange = viewModel::onPasswordInputFieldValueChanged,
            onConfirmPasswordInputFieldValueChange = viewModel::onConfirmPasswordInputFieldValueChanged,
            isNameValid = state.isNameValid,
            isEmailValid = state.isEmailValid,
            isPasswordValid = state.isPasswordValid,
            isConfirmPasswordValid = state.isConfirmPasswordValid,
            isLogin = state.isLogin,
            onPrimaryButtonClicked = viewModel::onPrimaryButtonClicked,
            onSecondaryButtonClicked = viewModel::onSecondaryButtonClicked
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreenContent(
    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    onNameInputFieldValueChange: (String) -> Unit,
    onEmailInputFieldValueChange: (String) -> Unit,
    onPasswordInputFieldValueChange: (String) -> Unit,
    onConfirmPasswordInputFieldValueChange: (String) -> Unit,
    isNameValid: Boolean,
    isEmailValid: Boolean,
    isPasswordValid: Boolean,
    isConfirmPasswordValid: Boolean,
    isLogin: Boolean,
    onPrimaryButtonClicked: () -> Unit,
    onSecondaryButtonClicked: () -> Unit
) {

    val onNameValueChange = remember(name, onNameInputFieldValueChange) { { name: String -> onNameInputFieldValueChange(name) } }
    val onEmailValueChange = remember(email, onNameInputFieldValueChange) { { email: String -> onEmailInputFieldValueChange(email) } }
    val onPasswordValueChange =
        remember(password, onNameInputFieldValueChange) { { password: String -> onPasswordInputFieldValueChange(password) } }
    val onConfirmPasswordValueChange = remember(
        confirmPassword,
        onNameInputFieldValueChange
    ) { { confirmPassword: String -> onConfirmPasswordInputFieldValueChange(confirmPassword) } }

    val focusManager = LocalFocusManager.current

    val titleText = if (isLogin) "Login" else "Register"
    val buttonText = if (isLogin) "Login" else "Register"
    val descriptionText = if (isLogin) "Don't have an account yet?" else "Already have an account?"
    val secondaryButtonText = if (isLogin) "Register" else "Login"
    val isPrimaryButtonEnabled =
        if (isLogin) isEmailValid && isPasswordValid && email.isNotBlank() && password.isNotBlank()
        else isNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid &&
            name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = spacing_3),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = titleText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing_3),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        if (!isLogin) {
            OutlinedTextField(
                value = name,
                textStyle = TextStyle(color = Color.Black),
                onValueChange = onNameValueChange,
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = spacing_2),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
                isError = !isNameValid
            )
            if (!isNameValid) {
                Text("Name cannot be empty!", color = md_theme_dark_error)
            }
        }

        OutlinedTextField(
            value = email,
            onValueChange = { onEmailValueChange(it) },
            label = { Text("Email") },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spacing_1),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
            },
            isError = !isEmailValid
        )
        if (!isEmailValid) {
            Text("Invalid email address!", color = md_theme_dark_error)
        }

        OutlinedTextField(
            value = password,
            onValueChange = { onPasswordValueChange(it) },
            label = { Text("Password") },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spacing_1),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
            },
            isError = !isPasswordValid
        )
        if (!isPasswordValid) {
            Text("Invalid password!", color = md_theme_dark_error)
        }

        if (!isLogin) {
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { onConfirmPasswordValueChange(it) },
                label = { Text("Confirm Password") },
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = spacing_1),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
                isError = !isConfirmPasswordValid
            )
            if (!isConfirmPasswordValid) {
                Text("Passwords do not match!", color = md_theme_dark_error)
            }
        }

        Spacer(modifier = Modifier.height(spacing_2))

        Button(
            onClick = { onPrimaryButtonClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spacing_1),
            enabled = isPrimaryButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.Gray
            ),
            contentPadding = PaddingValues(spacing_2)
        ) {
            Text(buttonText.uppercase())
        }

        Text(
            text = descriptionText,
            modifier = Modifier
                .padding(top = spacing_3)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        OutlinedButton(
            onClick = {
                focusManager.clearFocus()
                onSecondaryButtonClicked()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = spacing_1),
            contentPadding = PaddingValues(spacing_2)
        ) {
            Text(secondaryButtonText.uppercase())
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    AuthScreenContent(
        name = "Kristina",
        email = "test@test.com",
        password = "password",
        confirmPassword = "password",
        onNameInputFieldValueChange = {},
        onEmailInputFieldValueChange = {},
        onPasswordInputFieldValueChange = {},
        onConfirmPasswordInputFieldValueChange = {},
        isNameValid = true,
        isEmailValid = true,
        isPasswordValid = true,
        isConfirmPasswordValid = true,
        isLogin = false,
        onPrimaryButtonClicked = {},
        onSecondaryButtonClicked = {}
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    AuthScreenContent(
        name = "",
        email = "test@test.com",
        password = "password",
        confirmPassword = "",
        onNameInputFieldValueChange = {},
        onEmailInputFieldValueChange = {},
        onPasswordInputFieldValueChange = {},
        onConfirmPasswordInputFieldValueChange = {},
        isNameValid = true,
        isEmailValid = true,
        isPasswordValid = true,
        isConfirmPasswordValid = true,
        isLogin = true,
        onPrimaryButtonClicked = {},
        onSecondaryButtonClicked = {}
    )
}
