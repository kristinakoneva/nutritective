package com.kristinakoneva.nutritective.ui.screens.auth

import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : BaseViewModel<AuthState, Unit>(AuthState.Initial)
