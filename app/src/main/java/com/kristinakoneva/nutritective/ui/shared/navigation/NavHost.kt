package com.kristinakoneva.nutritective.ui.shared.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.screens.analyzetext.AnalyzeTextScreen
import com.kristinakoneva.nutritective.ui.screens.auth.AuthScreen
import com.kristinakoneva.nutritective.ui.screens.detectioninfo.DetectionInfoScreen
import com.kristinakoneva.nutritective.ui.screens.explorerecipes.ExploreRecipesScreen
import com.kristinakoneva.nutritective.ui.screens.foodproductdetails.FoodProductDetailsScreen
import com.kristinakoneva.nutritective.ui.screens.inspectimage.InspectImageScreen
import com.kristinakoneva.nutritective.ui.screens.opencamera.OpenCameraScreen
import com.kristinakoneva.nutritective.ui.screens.scanbarcode.ScanBarcodeScreen
import com.kristinakoneva.nutritective.ui.screens.selectallergens.SelectAllergensScreen
import com.kristinakoneva.nutritective.ui.screens.usersettings.UserSettingsScreen
import com.kristinakoneva.nutritective.ui.shared.composables.BottomNavigationBar
import com.kristinakoneva.nutritective.ui.theme.floating_button_image_size
import com.kristinakoneva.nutritective.ui.theme.floating_button_size
import com.kristinakoneva.nutritective.ui.theme.spacing_5_5

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
            WithBottomNavigationBar(navController, screenContent = {
                ScanBarcodeScreen(
                    onNavigateToOpenCamera = {
                        navController.navigate(ScreenRoute.OPEN_CAMERA)
                    },
                    onNavigateToFoodProductDetails = {
                        navController.navigate(ScreenRoute.FOOD_PRODUCT_DETAILS) {
                            popUpTo(ScreenRoute.SCAN_BARCODE) {
                                inclusive = false
                            }
                        }
                    }
                )
            })
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
        composable(ScreenRoute.USER_SETTINGS) {
            WithoutBottomNavigationBar(screenContent = {
                UserSettingsScreen(
                    onNavigateToDetectionInfo = {
                        navController.navigate(ScreenRoute.DETECTION_INFO)
                    },
                    onNavigateToSelectAllergens = {
                        navController.navigate(ScreenRoute.SELECT_ALLERGENS)
                    },
                    onNavigateBack = {
                        navController.navigateUp()
                    },
                    onNavigateToAuth = {
                        navController.navigate(ScreenRoute.AUTH) {
                            popUpTo(ScreenRoute.AUTH) {
                                inclusive = true
                            }
                        }
                    }
                )
            })
        }
        composable(ScreenRoute.OPEN_CAMERA) {
            WithoutBottomNavigationBar(screenContent = {
                OpenCameraScreen(
                    onNavigateBack = {
                        navController.navigateUp()
                    },
                    onNavigateToFoodProductDetails = {
                        navController.navigate(ScreenRoute.FOOD_PRODUCT_DETAILS) {
                            popUpTo(ScreenRoute.SCAN_BARCODE) {
                                inclusive = false
                            }
                        }
                    }
                )
            })
        }
        composable(ScreenRoute.FOOD_PRODUCT_DETAILS) {
            WithoutBottomNavigationBar(screenContent = {
                FoodProductDetailsScreen(
                    onNavigateToScanBarcode = {
                        navController.navigate(ScreenRoute.SCAN_BARCODE) {
                            popUpTo(ScreenRoute.SCAN_BARCODE) {
                                inclusive = true
                            }
                        }
                    }
                )
            })
        }
        composable(ScreenRoute.SELECT_ALLERGENS) {
            WithoutBottomNavigationBar(screenContent = {
                SelectAllergensScreen(
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            })
        }
        composable(ScreenRoute.DETECTION_INFO) {
            WithoutBottomNavigationBar(screenContent = {
                DetectionInfoScreen(
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            })
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
                modifier = Modifier
                    .offset(y = spacing_5_5)
                    .size(floating_button_size),
                onClick = {
                    navController.navigate(ScreenRoute.USER_SETTINGS)
                }
            ) {
                Image(
                    modifier = Modifier.size(floating_button_image_size),
                    painter = painterResource(id = R.mipmap.ic_launcher_round),
                    contentDescription = "Floating action button"
                )
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
        ScanBarcodeScreen(onNavigateToOpenCamera = {}, onNavigateToFoodProductDetails = {})
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
