package com.kristinakoneva.nutritective.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.screens.TestEvent
import com.kristinakoneva.nutritective.ui.screens.TestEvent2
import com.kristinakoneva.nutritective.ui.screens.TestViewModel
import com.kristinakoneva.nutritective.ui.screens.TestViewModel2
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.base.NavHost
import com.kristinakoneva.nutritective.ui.theme.NutritectiveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutritectiveTheme {
                NavHost()
            }
        }
    }
}

@Composable
fun GreetingScreen(
    onNavigateToSecondScreen: (String) -> Unit,
    viewModel: TestViewModel = hiltViewModel(),
) {
    BaseScreen(
        viewModel = viewModel,
        eventHandler = {
            when (it) {
                is TestEvent.NavigateToSecondScreen -> {
                    onNavigateToSecondScreen(it.text)
                }
            }
        },
        content = { state ->
            Column {
                Text(text = state.text)
                Button(onClick = viewModel::navigateToSecondScreen) {
                    Text(text = "Navigate to second screen")
                }
            }
        }
    )
}

@Composable
fun SecondScreen(
    onBackClicked: () -> Unit,
    viewModel: TestViewModel2 = hiltViewModel(),
) {
    val context = LocalContext.current
    BaseScreen(
        viewModel = viewModel,
        eventHandler = {
            when (it) {
                is TestEvent2.GoBack -> {
                    onBackClicked()
                }
            }
        },
        content = { state ->
            Column {
                Text(text = state.text)
                Button(onClick = { onBackClicked() }) {
                    Text(text = "Go back")
                }
            }
        }
    )
}