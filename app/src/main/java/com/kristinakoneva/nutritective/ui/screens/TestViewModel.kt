package com.kristinakoneva.nutritective.ui.screens

import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import com.kristinakoneva.nutritective.ui.shared.di.qualifiers.TextArgument
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
) : BaseViewModel<TestState, TestEvent>() {

    init {
        viewState = TestState(text = "lala")
    }

    fun navigateToSecondScreen() {
        emitEvent(TestEvent.NavigateToSecondScreen("Navigating to second screen!"))
    }
}

data class TestState(
    val text: String
)

sealed interface TestEvent {
    data class NavigateToSecondScreen(val text: String) : TestEvent
}

@HiltViewModel
class TestViewModel2 @Inject constructor(
    @TextArgument private val text: String
) : BaseViewModel<TestState2, TestEvent2>() {

    init {
        viewState = TestState2(text = text)
    }

    fun onGoBackClicked() {
        emitEvent(TestEvent2.GoBack())
    }
}

data class TestState2(
    val text: String
)

sealed interface TestEvent2 {
    class GoBack : TestEvent2
}




