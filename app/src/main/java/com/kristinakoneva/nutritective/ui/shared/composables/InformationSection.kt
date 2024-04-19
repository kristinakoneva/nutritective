package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.kristinakoneva.nutritective.ui.theme.spacing_1

@Composable
fun InformationSection(
    modifier: Modifier = Modifier,
    subtitle: String,
    value: String
) {
    Column(
        modifier = modifier,
    ) {
        Text(text = subtitle, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text(modifier = Modifier.padding(top = spacing_1), text = value, style = MaterialTheme.typography.bodyMedium)
    }
}
