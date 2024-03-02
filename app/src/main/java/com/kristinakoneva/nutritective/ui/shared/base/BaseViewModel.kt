package com.kristinakoneva.nutritective.ui.shared.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Event> : ViewModel() {

    private val mutableStateFlow: MutableStateFlow<State?> = MutableStateFlow(null)
    private val mutableLoadingStateFlow: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.Idle())
    private val mutableEventFlow = Channel<Event>(capacity = 1)
    private val mutableErrorFlow = Channel<ErrorEvent>(capacity = 1)

    val stateFlow: StateFlow<State?> = mutableStateFlow
    val loadingStateFlow: StateFlow<LoadingState> = mutableLoadingStateFlow
    val eventFlow: SingleSubscriberFlow<Event> = SingleSubscriberFlow(mutableEventFlow.receiveAsFlow())
    val errorFlow: SingleSubscriberFlow<ErrorEvent> = SingleSubscriberFlow(mutableErrorFlow.receiveAsFlow())

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        hideLoading()
        handleError(throwable)
    }

    protected var viewState: State?
        get() = mutableStateFlow.value
        set(value) {
            mutableStateFlow.value = value
        }

    private var loadingState: LoadingState
        get() = mutableLoadingStateFlow.value
        set(value) {
            mutableLoadingStateFlow.value = value
        }

    protected fun emitEvent(event: Event) {
        viewModelScope.launch {
            mutableEventFlow.send(event)
        }
    }

    private fun handleError(throwable: Throwable?) {
        showError(ErrorEvent.UnknownError())
    }

    private fun showError(error: ErrorEvent) {
        viewModelScope.launch {
            mutableErrorFlow.send(error)
        }
    }

    private fun showLoading() {
        loadingState = LoadingState.Loading()
    }

    private fun hideLoading() {
        loadingState = LoadingState.Idle()
    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.Main, block = block)

    protected fun launchWithLoading(block: suspend CoroutineScope.() -> Unit) {
        launch {
            showLoading()
            block()
            hideLoading()
        }
    }
}