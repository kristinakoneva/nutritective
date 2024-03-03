package com.kristinakoneva.nutritective.extensions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.kristinakoneva.nutritective.ui.shared.composables.BottomNavigationBar
import androidx.compose.ui.Modifier

@Composable
fun (@Composable () -> Unit).WithBottomNavigationBar(
    navController: NavController
) {
    val screenContent = this
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            screenContent()
        }
    }
}

@Composable
fun (@Composable () -> Unit).WithoutBottomNavigationBar(
) {
    val screenContent = this
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            screenContent()
        }
    }
}