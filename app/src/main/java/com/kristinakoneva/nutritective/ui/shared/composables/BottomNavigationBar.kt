package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kristinakoneva.nutritective.ui.shared.utils.BottomNavTab
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_onSurfaceVariant
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_primary

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        BottomNavTab.entries.forEach { item ->
            if (item.route != null && item.label != null && item.icon != null) {
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(item.icon, contentDescription = stringResource(item.label)) },
                    label = {
                        Text(
                            text = stringResource(id = item.label),
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = if (currentRoute == item.route) FontWeight.ExtraBold else FontWeight.Medium
                        )
                    },
                    colors = NavigationBarItemDefaults
                        .colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            indicatorColor = md_theme_dark_primary,
                            unselectedIconColor = md_theme_dark_onSurfaceVariant,
                            unselectedTextColor = md_theme_dark_onSurfaceVariant,
                        )
                )
            } else {
                NavigationBarItem(icon = {}, onClick = {}, selected = false, enabled = false)
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        navController = NavController(
            context = LocalContext.current
        )
    )
}
