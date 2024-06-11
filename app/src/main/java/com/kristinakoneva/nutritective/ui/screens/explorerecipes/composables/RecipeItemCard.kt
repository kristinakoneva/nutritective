package com.kristinakoneva.nutritective.ui.screens.explorerecipes.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kristinakoneva.nutritective.ui.screens.explorerecipes.RecipeItem
import com.kristinakoneva.nutritective.ui.shared.composables.AllergenStatusCard
import com.kristinakoneva.nutritective.ui.theme.food_item_card_corner_radius
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_primary
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_secondary
import com.kristinakoneva.nutritective.ui.theme.spacing_0_5
import com.kristinakoneva.nutritective.ui.theme.spacing_2

@Composable
fun RecipeItemCard(
    recipeItem: RecipeItem,
    onClickAction: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = md_theme_dark_secondary,
                shape = RoundedCornerShape(food_item_card_corner_radius)
            )
            .clickable {
                onClickAction()
            },
        elevation = CardDefaults.cardElevation(spacing_0_5),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(md_theme_dark_secondary)
        ) {
            Row(modifier = Modifier.padding(spacing_2)) {
                AsyncImage(model = recipeItem.recipe.imageUrl, contentDescription = "Recipe image")
                Column(modifier = Modifier.padding(start = spacing_2)) {
                    Text(
                        text = recipeItem.recipe.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        color = md_theme_dark_primary,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Source: " + recipeItem.recipe.source,
                        style = MaterialTheme.typography.titleSmall,
                        color = md_theme_dark_primary,
                        textAlign = TextAlign.Start
                    )
                    recipeItem.recipe.cuisineType?.let {
                        Text(
                            text = "Cuisine type: $it",
                            style = MaterialTheme.typography.titleSmall,
                            color = md_theme_dark_primary,
                            textAlign = TextAlign.Start
                        )
                    }
                    recipeItem.recipe.dishType?.let {
                        Text(
                            text = "Dish type: $it",
                            style = MaterialTheme.typography.titleSmall,
                            color = md_theme_dark_primary,
                            textAlign = TextAlign.Start
                        )
                    }
                    recipeItem.recipe.mealType?.let {
                        Text(
                            text = "Meal type: $it",
                            style = MaterialTheme.typography.titleSmall,
                            color = md_theme_dark_primary,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
            if (recipeItem.allergenStatus != null) {
                Column(modifier = Modifier.padding(horizontal = spacing_2)) {
                    AllergenStatusCard(
                        allergenStatus = recipeItem.allergenStatus,
                        detectedAllergens = recipeItem.detectedAllergens
                    )
                }
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing_2),
                border = BorderStroke(width = 2.dp, color = md_theme_dark_primary),
                onClick = onClickAction,
            ) {
                Text("View details", textAlign = TextAlign.Center)
            }
        }
    }
}
