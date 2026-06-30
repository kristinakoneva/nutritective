package com.kristinakoneva.nutritective.ui.screens.usersettings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.shared.accessibility.actionSemantics
import com.kristinakoneva.nutritective.ui.shared.accessibility.hideFromSemanticTree
import com.kristinakoneva.nutritective.ui.shared.accessibility.listItem
import com.kristinakoneva.nutritective.ui.theme.medium_icon_size
import com.kristinakoneva.nutritective.ui.theme.spacing_1

@Composable
fun AllergenListedItem(
    name: String,
    onClick: () -> Unit,
    onRemoveAllergen: () -> Unit,
    listIndex: Int? = null,
) {
    val avoidProductsContentDescription = stringResource(R.string.cd_avoid_products_for, name)
    var itemModifier = Modifier.fillMaxWidth()
    if (listIndex != null) {
        itemModifier = itemModifier.listItem(listIndex)
    }
    Column(modifier = itemModifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = spacing_1),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(onClick = onRemoveAllergen) {
                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = stringResource(R.string.cd_remove_allergen, name),
                    modifier = Modifier.size(medium_icon_size)
                )
            }
        }
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = spacing_1)
                .actionSemantics(avoidProductsContentDescription),
        ) {
            Text(
                text = stringResource(R.string.avoid_products),
                modifier = Modifier.hideFromSemanticTree(),
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier
                    .size(medium_icon_size)
                    .hideFromSemanticTree(),
            )
        }
    }
}
