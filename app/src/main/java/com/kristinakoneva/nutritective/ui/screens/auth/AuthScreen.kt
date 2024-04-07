package com.kristinakoneva.nutritective.ui.screens.auth

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) { state ->
        Text(text = "Auth Screen")
    }
}
