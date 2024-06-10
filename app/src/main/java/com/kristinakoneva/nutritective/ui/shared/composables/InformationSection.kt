package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_4

@Composable
fun InformationSection(
    modifier: Modifier = Modifier,
    subtitle: String,
    value: String
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = subtitle,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(top = spacing_1)
                .fillMaxSize(), text = value, style = MaterialTheme.typography.bodyLarge
        )
    }
}
