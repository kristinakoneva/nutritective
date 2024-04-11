package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kristinakoneva.nutritective.ui.theme.default_corner_radius
import com.kristinakoneva.nutritective.ui.theme.loading_box_size

@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = {},
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(loading_box_size)
                .background(Color.White, shape = RoundedCornerShape(default_corner_radius))
        ) {
            CircularProgressIndicator()
        }
    }
}
