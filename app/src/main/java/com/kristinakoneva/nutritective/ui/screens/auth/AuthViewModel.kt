package com.kristinakoneva.nutritective.ui.screens.auth

import com.kristinakoneva.nutritective.domain.authentication.AuthRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<AuthState, AuthEvent>(
    AuthState(isLogin = true)
) {

    companion object {
        private const val MIN_PASSWORD_LENGTH = 6
    }

    private var isLogin: Boolean = true

    fun onNameInputFieldValueChanged(input: String) {
        viewState = viewState.copy(name = input, isNameValid = input.isNotBlank())
    }

    fun onEmailInputFieldValueChanged(input: String) {
        viewState = viewState.copy(
            email = input,
            isEmailValid = input.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
        )
    }

    fun onPasswordInputFieldValueChanged(input: String) {
        viewState = viewState.copy(
            password = input,
            isPasswordValid = input.isNotBlank() && input.length >= MIN_PASSWORD_LENGTH
        )
    }

    fun onConfirmPasswordInputFieldValueChanged(input: String) {
        viewState = viewState.copy(
            confirmPassword = input,
            isConfirmPasswordValid = input.isNotBlank() && input == viewState.password
        )
    }

    fun onPrimaryButtonClicked() {
        launchWithLoading {
            try {
                if (isLogin) {
                    authRepository.loginUser(viewState.email, viewState.password)
                } else {
                    authRepository.registerUser(viewState.email, viewState.password)
                    authRepository.updateUserDisplayName(viewState.name)
                }
                if (authRepository.getCurrentUser() != null) {
                    emitEvent(AuthEvent.SuccessfulAuth)
                } else {
                    emitEvent(AuthEvent.FailedAuth)
                }
            } catch (e: Exception) {
                emitEvent(AuthEvent.FailedAuth)
            } finally {
                resetFields()
            }
        }
    }

    fun onSecondaryButtonClicked() {
        isLogin = !isLogin
        resetFields()
        viewState = viewState.copy(
            isLogin = isLogin
        )
    }

    private fun resetFields() {
        viewState = viewState.copy(
            name = "",
            email = "",
            password = "",
            confirmPassword = "",
            isNameValid = true,
            isEmailValid = true,
            isPasswordValid = true,
            isConfirmPasswordValid = true
        )
    }
}
