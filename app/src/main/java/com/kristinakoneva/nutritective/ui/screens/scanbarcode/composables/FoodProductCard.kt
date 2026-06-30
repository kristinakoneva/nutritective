package com.kristinakoneva.nutritective.ui.screens.scanbarcode.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import com.kristinakoneva.nutritective.ui.shared.accessibility.actionSemantics
import com.kristinakoneva.nutritective.ui.shared.accessibility.hideFromSemanticTree
import com.kristinakoneva.nutritective.ui.shared.accessibility.informationSemantics
import com.kristinakoneva.nutritective.ui.shared.composables.AllergenStatusCard
import com.kristinakoneva.nutritective.ui.shared.models.AllergenStatus
import com.kristinakoneva.nutritective.ui.theme.food_item_card_corner_radius
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_primary
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_secondary
import com.kristinakoneva.nutritective.ui.theme.nutriscore_image_height
import com.kristinakoneva.nutritective.ui.theme.nutriscore_image_width
import com.kristinakoneva.nutritective.ui.theme.spacing_0_5
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2

@Composable
fun FoodProductCard(
    foodProduct: FoodProduct,
    onClickAction: () -> Unit,
    allergenStatus: AllergenStatus? = null,
    detectedAllergens: List<String>? = null
) {
    val productName = foodProduct.name.orEmpty()
    val summaryDescription = stringResource(
        R.string.cd_food_product_summary,
        productName,
        foodProduct.brands.orEmpty().ifBlank { "-" },
    )
    val viewDetailsDescription = stringResource(R.string.cd_view_food_item_details, productName)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = md_theme_dark_secondary,
                shape = RoundedCornerShape(food_item_card_corner_radius)
            ),
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
                        model = foodProduct.imageUrl,
                        contentDescription = null,
                    )
                    Column(modifier = Modifier.padding(start = spacing_1)) {
                        Text(
                            text = productName,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            color = md_theme_dark_primary,
                            textAlign = TextAlign.Start,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(foodProduct.nutriscoreUrl)
                                    .decoderFactory(SvgDecoder.Factory())
                                    .build(),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(top = spacing_0_5)
                                    .size(height = nutriscore_image_height, width = nutriscore_image_width)
                            )
                        }
                    }
                }
            }
            if (allergenStatus != null) {
                Column(modifier = Modifier.padding(horizontal = spacing_2)) {
                    AllergenStatusCard(
                        allergenStatus = allergenStatus,
                        detectedAllergens = detectedAllergens,
                        subjectName = productName,
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
