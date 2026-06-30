package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.ui.shared.accessibility.actionSemantics
import com.kristinakoneva.nutritective.ui.shared.accessibility.hideFromSemanticTree
import com.kristinakoneva.nutritective.ui.shared.accessibility.informationSemantics
import com.kristinakoneva.nutritective.ui.shared.accessibility.listItem
import com.kristinakoneva.nutritective.ui.theme.food_item_card_corner_radius
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_primary
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_secondary
import com.kristinakoneva.nutritective.ui.theme.spacing_0_5
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import java.util.Locale

@Composable
fun FoodItemCard(
    foodItem: FoodItem,
    onClickAction: (FoodItem) -> Unit,
    listIndex: Int? = null,
) {
    val displayName = foodItem.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    val summaryDescription = stringResource(
        R.string.cd_food_item_summary,
        displayName,
        foodItem.calories,
        foodItem.servingSize,
    )
    val viewDetailsDescription = stringResource(R.string.cd_view_food_item_details, displayName)
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
                Text(
                    modifier = Modifier
                        .padding(horizontal = spacing_2)
                        .padding(top = spacing_2)
                        .padding(bottom = spacing_1),
                    text = displayName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = md_theme_dark_primary
                )
                FoodItemShortSummary(calories = foodItem.calories, servingSize = foodItem.servingSize)
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing_2)
                    .actionSemantics(viewDetailsDescription),
                border = BorderStroke(width = 2.dp, color = md_theme_dark_primary),
                onClick = { onClickAction(foodItem) },
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
