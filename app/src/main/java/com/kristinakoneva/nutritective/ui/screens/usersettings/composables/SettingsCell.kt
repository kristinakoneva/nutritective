package com.kristinakoneva.nutritective.ui.screens.usersettings.composables

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.shared.accessibility.actionSemantics
import com.kristinakoneva.nutritective.ui.shared.accessibility.hideFromSemanticTree
import com.kristinakoneva.nutritective.ui.theme.default_corner_radius
import com.kristinakoneva.nutritective.ui.theme.extra_large_icon_size
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_outline
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_primary
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_secondary
import com.kristinakoneva.nutritective.ui.theme.spacing_2

@Composable
fun SettingsCell(
    text: String,
    onClick: () -> Unit
) {
    val navigateDescription = if (text.lastOrNull() in listOf('.', '?', '!')) {
        stringResource(R.string.cd_settings_navigate_after_punctuation, text)
    } else {
        stringResource(R.string.cd_settings_navigate, text)
    }
    val interactionSource = remember { MutableInteractionSource() }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = true, color = md_theme_dark_outline)
            ) { onClick() }
            .actionSemantics(navigateDescription)
            .background(
                color = md_theme_dark_secondary,
                shape = RoundedCornerShape(default_corner_radius)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(md_theme_dark_secondary),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .weight(1f)
                    .padding(spacing_2)
                    .hideFromSemanticTree(),
                style = MaterialTheme.typography.titleMedium,
                color = md_theme_dark_primary,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                modifier = Modifier
                    .size(extra_large_icon_size)
                    .padding(end = spacing_2),
                tint = md_theme_dark_primary,
            )
        }
    }
}
