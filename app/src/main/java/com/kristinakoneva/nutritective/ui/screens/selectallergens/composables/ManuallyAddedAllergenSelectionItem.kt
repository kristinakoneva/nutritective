package com.kristinakoneva.nutritective.ui.screens.selectallergens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.shared.accessibility.gridItem
import com.kristinakoneva.nutritective.ui.shared.accessibility.hideFromSemanticTree
import com.kristinakoneva.nutritective.ui.shared.accessibility.selectableSemantics
import com.kristinakoneva.nutritective.ui.theme.default_corner_radius
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_onSecondary
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_onSecondaryContainer
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_primary
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_secondary
import com.kristinakoneva.nutritective.ui.theme.spacing_0_5
import com.kristinakoneva.nutritective.ui.theme.spacing_1

@Composable
fun ManuallyAddedAllergenSelectionItem(
    name: String,
    isSelected: Boolean = true,
    modifier: Modifier,
    onClick: () -> Unit,
    gridIndex: Int? = null,
) {
    val selectedLabel = stringResource(R.string.cd_selected)
    val notSelectedLabel = stringResource(R.string.cd_not_selected)
    val allergenLabel = stringResource(R.string.cd_allergen_toggle, name)
    var itemModifier = modifier
        .selectableSemantics(
            label = allergenLabel,
            selected = isSelected,
            selectedDescription = selectedLabel,
            notSelectedDescription = notSelectedLabel,
        )
        .clickable { onClick() }
    if (gridIndex != null) {
        itemModifier = itemModifier.gridItem(gridIndex, columnCount = 3)
    }
    var columnModifier = Modifier.fillMaxSize()
    columnModifier = if (isSelected) {
        columnModifier
            .border(4.dp, md_theme_dark_primary)
            .background(md_theme_dark_secondary)
    } else {
        columnModifier
            .background(md_theme_dark_onSecondaryContainer)
    }
    Card(modifier = itemModifier, shape = RoundedCornerShape(default_corner_radius)) {
        Column(modifier = columnModifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(spacing_1))
            Text(
                modifier = Modifier
                    .padding(bottom = spacing_1)
                    .padding(horizontal = spacing_0_5)
                    .hideFromSemanticTree(),
                text = name,
                color = md_theme_dark_onSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
