package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.ui.theme.food_item_card_corner_radius
import com.kristinakoneva.nutritective.ui.theme.large_icon_size
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_onSecondary
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_primary
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_secondary
import com.kristinakoneva.nutritective.ui.theme.spacing_0_5
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import java.util.Locale

@Composable
fun FoodItemCard(foodItem: FoodItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = md_theme_dark_secondary,
                shape = RoundedCornerShape(food_item_card_corner_radius)
            )
            .clickable { /* Handle card click */ },
        elevation = CardDefaults.cardElevation(spacing_0_5),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(md_theme_dark_secondary)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = spacing_2)
                    .padding(top = spacing_2)
                    .padding(bottom = spacing_1),
                text = foodItem.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                color = md_theme_dark_primary
            )
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
                    text = "Calories: ${foodItem.calories}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = md_theme_dark_onSecondary,
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
                    text = "Serving Size: ${foodItem.servingSize}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = md_theme_dark_onSecondary,
                    fontWeight = FontWeight.SemiBold
                )
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing_2),
                border = BorderStroke(width = 2.dp, color = md_theme_dark_primary),
                onClick = { /*TODO*/ },
            ) {
                Text("View details", textAlign = TextAlign.Center)
            }
        }
    }
}
