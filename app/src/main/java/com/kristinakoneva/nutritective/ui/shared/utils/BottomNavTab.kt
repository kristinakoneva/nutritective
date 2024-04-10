package com.kristinakoneva.nutritective.ui.shared.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CalendarViewDay
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.FilterCenterFocus
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.ViewArray
import androidx.compose.ui.graphics.vector.ImageVector
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.shared.constants.ScreenRoute

enum class BottomNavTab(
    val route: String?,
    val icon: ImageVector?,
    @StringRes val label: Int?
) {
    SCAN_BARCODE(ScreenRoute.SCAN_BARCODE, Icons.Default.ViewArray, R.string.scan_barcode),
    INSPECT_IMAGE(ScreenRoute.INSPECT_IMAGE, Icons.Default.ImageSearch, R.string.inspect_image),
    PLACEHOLDER(null, null, null),
    ANALYZE_TEXT(ScreenRoute.ANALYZE_TEXT, Icons.Default.TextFields, R.string.analyze_text),
    EXPLORE_RECIPES(ScreenRoute.EXPLORE_RECIPES, Icons.AutoMirrored.Filled.MenuBook, R.string.explore_recipes)
}
