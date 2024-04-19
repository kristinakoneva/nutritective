package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.theme.large_icon_size
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_onSecondary
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2

@Composable
fun FoodItemShortSummary(
    calories: String,
    servingSize: String,
    textColor: Color = md_theme_dark_onSecondary
) {
    Row(
        modifier = Modifier
            .padding(horizontal = spacing_2)
            .padding(vertical = spacing_1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_calories),
            contentDescription = "Calories icon",
            Modifier.size(large_icon_size)
        )
        Text(
            modifier = Modifier
                .padding(start = spacing_2),
            text = "Calories: $calories",
            style = MaterialTheme.typography.bodyLarge,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
    }
    Row(
        modifier = Modifier
            .padding(horizontal = spacing_2)
            .padding(vertical = spacing_1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_serving_size),
            contentDescription = "Serving size icon",
            Modifier.size(large_icon_size)
        )
        Text(
            modifier = Modifier
                .padding(start = spacing_2),
            text = "Serving Size: $servingSize",
            style = MaterialTheme.typography.bodyLarge,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}
