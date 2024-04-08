package com.kristinakoneva.nutritective.ui.shared.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kristinakoneva.nutritective.ui.screens.analyzetext.AnalyzeTextScreen
import com.kristinakoneva.nutritective.ui.screens.auth.AuthScreen
import com.kristinakoneva.nutritective.ui.screens.explorerecipes.ExploreRecipesScreen
import com.kristinakoneva.nutritective.ui.screens.inspectimage.InspectImageScreen
import com.kristinakoneva.nutritective.ui.screens.scanbarcode.ScanBarcodeScreen
import com.kristinakoneva.nutritective.ui.shared.constants.ScreenRoute
import com.kristinakoneva.nutritective.ui.shared.composables.BottomNavigationBar
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_5

@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoute.AUTH) {
        composable(ScreenRoute.AUTH) {
            WithoutBottomNavigationBar(screenContent = {
                AuthScreen(
                    onNavigateToScanBarcodeScreen = {
                        navController.navigate(ScreenRoute.SCAN_BARCODE) {
                            popUpTo(ScreenRoute.AUTH) {
                                inclusive = true
                            }
                        }
                    },
                )
            })
        }
        composable(ScreenRoute.SCAN_BARCODE) {
            WithBottomNavigationBar(navController, screenContent = { ScanBarcodeScreen() })
        }
        composable(ScreenRoute.INSPECT_IMAGE) {
            WithBottomNavigationBar(navController, screenContent = { InspectImageScreen() })
        }
        composable(ScreenRoute.ANALYZE_TEXT) {
            WithBottomNavigationBar(navController, screenContent = { AnalyzeTextScreen() })
        }
        composable(ScreenRoute.EXPLORE_RECIPES) {
            WithBottomNavigationBar(navController, screenContent = { ExploreRecipesScreen() })
        }
    }
}

@Composable
fun WithBottomNavigationBar(navController: NavController, screenContent: @Composable () -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                modifier = Modifier.offset(y = spacing_5),
                onClick = { /*TODO*/
                }
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "Floating action button")
            }
        }) { padding ->
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
