package com.kristinakoneva.nutritective.ui.shared.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun <State : Any, Event : Any> BaseScreen(
    viewModel: BaseViewModel<State, Event>,
    eventRenderer: (Event) -> Unit,
    stateRenderer: @Composable (State) -> Unit
) {
    viewModel.stateFlow.collectAsStateWithLifecycle().value?.let { state ->
        stateRenderer(state)
    }

    viewModel.loadingStateFlow.collectAsStateWithLifecycle().value.let { loadingState ->
        when (loadingState) {
            is LoadingState.Idle -> {
                // TODO
            }

            is LoadingState.Loading -> {
                // TODO
            }
        }
    }

    LaunchedEffect(viewModel.eventFlow) {
        viewModel.eventFlow.collectSafely(eventRenderer::invoke)
    }

    LaunchedEffect(viewModel.errorFlow) {
        viewModel.errorFlow.collectSafely { error ->
            // TODO
        }
    }
}