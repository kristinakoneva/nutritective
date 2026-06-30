package com.kristinakoneva.nutritective.ui.screens.explorerecipes.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.screens.explorerecipes.RecipeItem
import com.kristinakoneva.nutritective.ui.shared.accessibility.actionSemantics
import com.kristinakoneva.nutritective.ui.shared.accessibility.hideFromSemanticTree
import com.kristinakoneva.nutritective.ui.shared.accessibility.informationSemantics
import com.kristinakoneva.nutritective.ui.shared.accessibility.listItem
import com.kristinakoneva.nutritective.ui.shared.composables.AllergenStatusCard
import com.kristinakoneva.nutritective.ui.theme.food_item_card_corner_radius
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_primary
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_secondary
import com.kristinakoneva.nutritective.ui.theme.spacing_0_5
import com.kristinakoneva.nutritective.ui.theme.spacing_2

@Composable
fun RecipeItemCard(
    recipeItem: RecipeItem,
    listIndex: Int? = null,
    onClickAction: () -> Unit,
) {
    val recipe = recipeItem.recipe
    val recipeTitle = recipe.title
    val summaryParts = buildList {
        add(stringResource(R.string.cd_recipe_summary, recipeTitle, recipe.source))
        recipe.cuisineType?.let { add(stringResource(R.string.recipe_cuisine_type_label, it)) }
        recipe.dishType?.let { add(stringResource(R.string.recipe_dish_type_label, it)) }
        recipe.mealType?.let { add(stringResource(R.string.recipe_meal_type_label, it)) }
    }
    val summaryDescription = summaryParts.joinToString(separator = ". ")
    val viewDetailsDescription = stringResource(R.string.cd_open_recipe, recipeTitle)
    var cardModifier = Modifier
        .fillMaxSize()
        .background(
            color = md_theme_dark_secondary,
            shape = RoundedCornerShape(food_item_card_corner_radius)
        )
    if (listIndex != null) {
        cardModifier = cardModifier.listItem(listIndex)
    }
    Card(
        modifier = cardModifier,
        elevation = CardDefaults.cardElevation(spacing_0_5),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(md_theme_dark_secondary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .informationSemantics(summaryDescription),
            ) {
                Row(modifier = Modifier.padding(spacing_2)) {
                    AsyncImage(
                        model = recipe.imageUrl,
                        contentDescription = null,
                    )
                    Column(modifier = Modifier.padding(start = spacing_2)) {
                        Text(
                            text = recipeTitle,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            color = md_theme_dark_primary,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = stringResource(R.string.recipe_source_label, recipe.source),
                            style = MaterialTheme.typography.titleSmall,
                            color = md_theme_dark_primary,
                            textAlign = TextAlign.Start
                        )
                        recipe.cuisineType?.let {
                            Text(
                                text = stringResource(R.string.recipe_cuisine_type_label, it),
                                style = MaterialTheme.typography.titleSmall,
                                color = md_theme_dark_primary,
                                textAlign = TextAlign.Start
                            )
                        }
                        recipe.dishType?.let {
                            Text(
                                text = stringResource(R.string.recipe_dish_type_label, it),
                                style = MaterialTheme.typography.titleSmall,
                                color = md_theme_dark_primary,
                                textAlign = TextAlign.Start
                            )
                        }
                        recipe.mealType?.let {
                            Text(
                                text = stringResource(R.string.recipe_meal_type_label, it),
                                style = MaterialTheme.typography.titleSmall,
                                color = md_theme_dark_primary,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
            if (recipeItem.allergenStatus != null) {
                Column(modifier = Modifier.padding(horizontal = spacing_2)) {
                    AllergenStatusCard(
                        allergenStatus = recipeItem.allergenStatus,
                        detectedAllergens = recipeItem.detectedAllergens,
                        subjectName = recipeTitle,
                    )
                }
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing_2)
                    .actionSemantics(viewDetailsDescription),
                border = BorderStroke(width = 2.dp, color = md_theme_dark_primary),
                onClick = onClickAction,
            ) {
                Text(
                    stringResource(R.string.view_details),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.hideFromSemanticTree(),
                )
            }
        }
    }
}
