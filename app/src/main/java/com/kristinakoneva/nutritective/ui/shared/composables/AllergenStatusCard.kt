package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus
import com.kristinakoneva.nutritective.ui.theme.food_item_card_corner_radius
import com.kristinakoneva.nutritective.ui.theme.large_icon_size
import com.kristinakoneva.nutritective.ui.theme.spacing_0_5
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2

@Composable
fun AllergenStatusCard(
    allergenStatus: AllergenStatus,
    detectedAllergens: List<String>? = null
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(food_item_card_corner_radius),
            ),
        elevation = CardDefaults.cardElevation(spacing_0_5),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(allergenStatus.color)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = allergenStatus.iconResId),
                    contentDescription = "Allergen status icon",
                    modifier = Modifier
                        .padding(top = spacing_2)
                        .padding(start = spacing_2)
                        .padding(bottom = spacing_1)
                        .size(large_icon_size)
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = spacing_2)
                        .padding(top = spacing_2)
                        .padding(bottom = spacing_1),
                    text = allergenStatus.text,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                )
            }
            when (allergenStatus) {
                AllergenStatus.SAFE -> {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = spacing_2)
                            .padding(bottom = spacing_2),
                        text = "None of your selected allergens were detected.",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        fontStyle = FontStyle.Italic
                    )
                }

                AllergenStatus.WARNING -> {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = spacing_2)
                            .padding(bottom = spacing_2),
                        text = "Allergen status couldn't be determined.",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        fontStyle = FontStyle.Italic
                    )
                }

                AllergenStatus.DANGER -> {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = spacing_2)
                            .padding(bottom = spacing_2),
                        text = "Detected allergens: ${detectedAllergens?.joinToString()}",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}
