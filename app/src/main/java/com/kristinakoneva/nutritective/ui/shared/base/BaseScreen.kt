package com.kristinakoneva.nutritective.ui.shared.base

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.kristinakoneva.nutritective.ui.shared.composables.LoadingDialog

@Composable
fun <State : Any, Event : Any> BaseScreen(
    viewModel: BaseViewModel<State, Event>,
    eventHandler: (Event) -> Unit,
    content: @Composable (State) -> Unit,
) {
    val context = LocalContext.current
    val navController = rememberNavController()

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
        viewModel.errorFlow.collectSafely { _ ->
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }
}
