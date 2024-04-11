package com.kristinakoneva.nutritective.ui.shared.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kristinakoneva.nutritective.ui.shared.composables.LoadingDialog

@Composable
fun <State : Any, Event : Any> BaseScreen(
    viewModel: BaseViewModel<State, Event>,
    eventHandler: (Event) -> Unit,
    content: @Composable (State) -> Unit,
) {
    viewModel.stateFlow.collectAsStateWithLifecycle().value.let { state ->
        content(state)
    }

    viewModel.loadingStateFlow.collectAsStateWithLifecycle().value.let { loadingState ->
        when (loadingState) {
            is LoadingState.Idle -> Unit

            is LoadingState.Loading -> LoadingDialog()
        }
    }

    LaunchedEffect(viewModel.eventFlow) {
        viewModel.eventFlow.collectSafely(eventHandler::invoke)
    }

    LaunchedEffect(viewModel.errorFlow) {
        viewModel.errorFlow.collectSafely { error ->
            // TODO
        }
    }
}
