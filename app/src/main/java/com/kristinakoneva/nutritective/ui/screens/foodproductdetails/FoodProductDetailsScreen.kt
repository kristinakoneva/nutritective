package com.kristinakoneva.nutritective.ui.screens.foodproductdetails

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.AllergenStatusCard
import com.kristinakoneva.nutritective.ui.shared.composables.InformationSection
import com.kristinakoneva.nutritective.ui.shared.composables.NutrimentItem
import com.kristinakoneva.nutritective.ui.shared.models.AllergenStatus
import com.kristinakoneva.nutritective.ui.theme.nutriscore_image_height
import com.kristinakoneva.nutritective.ui.theme.nutriscore_image_width
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_8

@Composable
fun FoodProductDetailsScreen(
    viewModel: FoodProductDetailsViewModel = hiltViewModel(),
    onNavigateToScanBarcode: () -> Unit
) {
    BaseScreen(viewModel = viewModel, eventHandler = { event ->
        when (event) {
            is FoodProductDetailsEvent.OnNavigateToScanBarcode -> onNavigateToScanBarcode()
        }
    }) { state ->
        when (state) {
            is FoodProductDetailsState.Initial -> Unit
            is FoodProductDetailsState.Content -> {
                FoodProductDetailsScreenContent(
                    foodProduct = state.foodProduct,
                    allergenStatus = state.allergenStatus,
                    detectedAllergens = state.detectedAllergens,
                    onCloseButtonClicked = viewModel::onCloseButtonClicked
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FoodProductDetailsScreenContent(
    foodProduct: FoodProduct,
    allergenStatus: AllergenStatus?,
    detectedAllergens: List<String>?,
    onCloseButtonClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = foodProduct.name.orEmpty(),
                        maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onCloseButtonClicked) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close button")
                    }
                }
            )
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .padding(top = spacing_8)
                .padding(bottom = spacing_3)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .padding(top = spacing_2, bottom = spacing_2)
                    .padding(horizontal = spacing_3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = spacing_1)
                        .weight(0.6f)
                ) {
                    Text(text = foodProduct.name.orEmpty(), style = MaterialTheme.typography.headlineSmall)
                    Text(
                        text = "Brands: ${foodProduct.brands ?: "-"}",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(top = spacing_1)
                    )
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(foodProduct.nutriscoreUrl)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        contentDescription = "Nutri-Score Grade Image",
                        modifier = Modifier.size(height = nutriscore_image_height, width = nutriscore_image_width)
                    )
                }
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.4f),
                    model = foodProduct.imageUrl,
                    contentDescription = "Food Product Image"
                )
            }

            if (allergenStatus != null) {
                Column(modifier = Modifier.padding(horizontal = spacing_3, vertical = spacing_2)) {
                    AllergenStatusCard(allergenStatus = allergenStatus, detectedAllergens = detectedAllergens)
                }
            }

            val nutriments = foodProduct.nutriments ?: emptyList()
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing_3),
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
            InformationSection(
                modifier = Modifier.padding(spacing_3),
                subtitle = "Allergens",
                value = foodProduct.allergens?.joinToString(", ") ?: "No information available."
            )
            InformationSection(
                modifier = Modifier.padding(spacing_3),
                subtitle = "Ingredients",
                value = foodProduct.ingredients ?: "No information available."
            )
            InformationSection(
                modifier = Modifier.padding(spacing_3),
                subtitle = "Categories",
                value = foodProduct.categories ?: "No information available."
            )
        }
    }
}
