package com.kristinakoneva.nutritective.ui.screens.auth

sealed interface AuthState {
    data object Initial : AuthState

    data class Register(
        val name: String,
        val email: String,
        val password: String,
        val confirmPassword: String
    ) : AuthState

    data class Login(
        val email: String,
        val password: String
    ) : AuthState
}
