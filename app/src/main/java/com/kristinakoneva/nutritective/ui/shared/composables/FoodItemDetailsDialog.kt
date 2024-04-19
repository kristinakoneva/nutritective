package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FoodItemDetailsDialog(
    foodItem: FoodItem,
    onClose: () -> Unit,
) {

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text(text = foodItem.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }) },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                FoodItemShortSummary(calories = foodItem.calories, servingSize = foodItem.servingSize, textColor = Color.White)

                val nutriments = foodItem.nutriments
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = spacing_2)
                        .padding(horizontal = spacing_1),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    maxItemsInEachRow = 2
                ) {
                    val itemModifier = Modifier
                        .padding(vertical = spacing_1)
                        .weight(1f)
                    nutriments.forEach { nutriment ->
                        NutrimentItem(itemModifier, nutriment)
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onClose) {
                Text("Close")
            }
        }
    )
}
