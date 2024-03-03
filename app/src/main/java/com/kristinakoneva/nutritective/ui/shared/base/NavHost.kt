package com.kristinakoneva.nutritective.ui.shared.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kristinakoneva.nutritective.ui.screens.analyzetext.AnalyzeTextScreen
import com.kristinakoneva.nutritective.ui.screens.inspectimage.InspectImageScreen
import com.kristinakoneva.nutritective.ui.screens.scanbarcode.ScanBarcodeScreen
import com.kristinakoneva.nutritective.ui.shared.constants.ScreenRoute
import com.kristinakoneva.nutritective.ui.shared.composables.BottomNavigationBar

@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoute.SCAN_BARCODE) {
        composable(ScreenRoute.SCAN_BARCODE) {
            WithBottomNavigationBar(navController, screenContent = { ScanBarcodeScreen() })
        }
        composable(ScreenRoute.INSPECT_IMAGE) {
            WithBottomNavigationBar(navController, screenContent = { InspectImageScreen() })
        }
        composable(ScreenRoute.ANALYZE_TEXT) {
            WithBottomNavigationBar(navController, screenContent = { AnalyzeTextScreen() })
        }
    }
}

@Composable
fun WithBottomNavigationBar(navController: NavController, screenContent: @Composable () -> Unit) {
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

@Preview
@Composable
fun WithBottomNavigationBarPreview() {
    WithBottomNavigationBar(navController = rememberNavController()) {
        ScanBarcodeScreen()
    }
}

@Composable
fun WithoutBottomNavigationBar(screenContent: @Composable () -> Unit) {
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

@Preview
@Composable
fun WithoutBottomNavigationBarPreview() {
    WithoutBottomNavigationBar {
        InspectImageScreen()
    }
}