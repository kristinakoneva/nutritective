package com.kristinakoneva.nutritective.ui.screens.auth

import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<AuthState, AuthEvent>(
    AuthState(isLogin = true)
) {

    companion object {
        private const val MIN_PASSWORD_LENGTH = 6
    }

    private var isLogin: Boolean = true

    init {
        launchWithLoading {
            if (userRepository.getCurrentUser() != null) {
                emitEvent(AuthEvent.SuccessfulAuth)
            }
        }
    }

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
                    userRepository.loginUser(viewState.email, viewState.password)
                } else {
                    userRepository.registerUser(viewState.email, viewState.password)
                    userRepository.updateUserDisplayName(viewState.name)
                }
                if (userRepository.getCurrentUser() != null) {
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
