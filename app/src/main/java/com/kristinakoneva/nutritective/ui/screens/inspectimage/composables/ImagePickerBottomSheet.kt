package com.kristinakoneva.nutritective.ui.screens.inspectimage.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2

@Composable
fun ImagePickerBottomSheet(
    onDismiss: () -> Unit,
    onTakePhotoClick: () -> Unit,
    onPhotoGalleryClick: () -> Unit
) {
    MyModalBottomSheetContent(
        onDismiss = {
            onDismiss.invoke()
        },
        items = listOf(
            BottomSheetItem(
                titleRes = R.string.take_photo_now,
                icon = Icons.Default.Camera,
                onClick = {
                    onTakePhotoClick.invoke()
                }
            ),
            BottomSheetItem(
                titleRes = R.string.select_image_from_gallery,
                icon = Icons.Default.Image,
                onClick = {
                    onPhotoGalleryClick.invoke()
                }
            ),
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyModalBottomSheetContent(
    onDismiss: () -> Unit,
    items: List<BottomSheetItem> = listOf(),
) {
    val skipPartiallyExpanded by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val edgeToEdgeEnabled by remember { mutableStateOf(false) }
    val windowInsets = if (edgeToEdgeEnabled)
        WindowInsets(0) else BottomSheetDefaults.windowInsets

    ModalBottomSheet(
        shape = MaterialTheme.shapes.medium.copy(
            bottomStart = CornerSize(0),
            bottomEnd = CornerSize(0)
        ),
        onDismissRequest = { onDismiss.invoke() },
        sheetState = bottomSheetState,
        windowInsets = windowInsets
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.forEach { item ->
                val title = stringResource(item.titleRes)
                androidx.compose.material3.ListItem(
                    modifier = Modifier
                        .clickable {
                            item.onClick.invoke()
                        }
                        .padding(horizontal = spacing_2, vertical = spacing_1),
                    headlineContent = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    leadingContent = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null
                        )
                    },
                )
            }
            Spacer(modifier = Modifier.height(spacing_2))
        }
    }
}

data class BottomSheetItem(
    @StringRes val titleRes: Int,
    val icon: ImageVector,
    val onClick: () -> Unit
)
