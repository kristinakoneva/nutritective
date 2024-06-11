package com.kristinakoneva.nutritective.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kristinakoneva.nutritective.ui.shared.navigation.NavHost
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
