package com.kristinakoneva.nutritective.ui.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.theme.NutritectiveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutritectiveTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(
    viewModel: TestViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    BaseScreen(
        viewModel = viewModel,
        eventRenderer = {
            when (it) {
                is TestEvent.ShowToast -> {
                    Toast.makeText(context, it.text, Toast.LENGTH_SHORT).show()
                }
            }
        },
        stateRenderer = { state ->
            Column {
                Text(text = state.text)
                Button(onClick = viewModel::onButtonClicked) {
                    Text(text = "Click me")
                }
            }
        }
    )
}