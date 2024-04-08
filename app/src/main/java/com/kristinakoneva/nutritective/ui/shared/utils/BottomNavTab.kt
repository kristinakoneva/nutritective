package com.kristinakoneva.nutritective.ui.shared.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.ui.graphics.vector.ImageVector
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.shared.constants.ScreenRoute

enum class BottomNavTab(
    val route: String?,
    val icon: ImageVector?,
    @StringRes val label: Int?
) {
    SCAN_BARCODE(ScreenRoute.SCAN_BARCODE, Icons.Default.Create, R.string.scan_barcode),
    INSPECT_IMAGE(ScreenRoute.INSPECT_IMAGE, Icons.Default.Create, R.string.inspect_image),
    PLACEHOLDER(null, null, null),
    ANALYZE_TEXT(ScreenRoute.ANALYZE_TEXT, Icons.Default.Create, R.string.analyze_text),
    EXPLORE_RECIPES(ScreenRoute.EXPLORE_RECIPES, Icons.Default.Create, R.string.explore_recipes)
}
