package com.kristinakoneva.nutritective.ui.screens.selectallergens

import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectAllergensViewModel @Inject constructor(
) : BaseViewModel<SelectAllergensState, Unit>(SelectAllergensState()){

}
