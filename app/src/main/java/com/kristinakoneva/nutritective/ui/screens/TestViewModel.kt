package com.kristinakoneva.nutritective.ui.screens

import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay

@HiltViewModel
class TestViewModel @Inject constructor(
) : BaseViewModel<TestState, TestEvent>() {

    init {
        launch {
            showText("Hey there!")
            delay(2000L)
            showText("Bye!")
        }
    }

    private fun showText(text: String) {
        viewState = TestState(text = text)
    }

    fun onButtonClicked() {
        emitEvent(TestEvent.ShowToast("Button clicked!"))
    }
}

data class TestState(
    val text: String
)

sealed interface TestEvent {
    data class ShowToast(val text: String) : TestEvent
}


