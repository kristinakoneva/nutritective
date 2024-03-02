package com.kristinakoneva.nutritective.ui.shared.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kristinakoneva.nutritective.ui.GreetingScreen
import com.kristinakoneva.nutritective.ui.SecondScreen
import com.kristinakoneva.nutritective.ui.shared.constants.NavArg
import com.kristinakoneva.nutritective.ui.shared.constants.NavArg.ARGUMENT_TEXT
import com.kristinakoneva.nutritective.ui.shared.constants.ScreenRoute

@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoute.GreetingScreen) {
        composable(ScreenRoute.GreetingScreen) {
            GreetingScreen(
                onNavigateToSecondScreen = { text ->
                    navController.navigate("${ScreenRoute.SecondScreen}/$text")
                }
            )
        }
        composable(route = "${ScreenRoute.SecondScreen}/{$ARGUMENT_TEXT}", arguments = listOf(
            navArgument(ARGUMENT_TEXT) {
                type = NavType.StringType
            }
        )) {
            SecondScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}