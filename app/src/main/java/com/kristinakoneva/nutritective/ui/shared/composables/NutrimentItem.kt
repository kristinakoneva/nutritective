package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.kristinakoneva.nutritective.domain.shared.models.Nutriment
import com.kristinakoneva.nutritective.ui.theme.large_icon_size
import com.kristinakoneva.nutritective.ui.theme.spacing_1

@Composable
fun NutrimentItem(modifier: Modifier, nutriment: Nutriment) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = nutriment.type.iconRes),
            contentDescription = nutriment.type.label,
            modifier = Modifier.size(large_icon_size)
        )
        Column(
            modifier = Modifier.padding(horizontal = spacing_1)
        ) {
            Text(text = nutriment.type.label, style = MaterialTheme.typography.bodySmall, maxLines = 1)
            Text(text = nutriment.value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
    }
}
