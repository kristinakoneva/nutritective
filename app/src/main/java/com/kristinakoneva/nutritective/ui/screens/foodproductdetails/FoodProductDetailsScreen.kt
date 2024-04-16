package com.kristinakoneva.nutritective.ui.screens.foodproductdetails

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import com.kristinakoneva.nutritective.domain.shared.models.Nutriment
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.theme.large_icon_size
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
    onCloseButtonClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = foodProduct.name.orEmpty()) },
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
                .padding(top = spacing_8, bottom = spacing_2)
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
                AsyncImage(model = foodProduct.imageUrl, contentDescription = "Food Product Image")
                Column(modifier = Modifier.padding(start = spacing_1)) {
                    Text(text = foodProduct.name.orEmpty(), style = MaterialTheme.typography.headlineLarge)
                    Text(text = "Brands: ${foodProduct.brands.orEmpty()}", style = MaterialTheme.typography.labelLarge)
                    //AsyncImage(model = foodProduct.nutriscoreUrl, contentDescription = "Nutri-Score Grade Image")
                }
            }
            val nutriments = foodProduct.nutriments ?: emptyList()
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing_2)
                    .padding(horizontal = spacing_3),
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
                modifier = Modifier
                    .padding(top = spacing_2)
                    .padding(horizontal = spacing_3),
                subtitle = "Allergens",
                value = foodProduct.allergens?.joinToString(", ").orEmpty()
            )
            InformationSection(
                modifier = Modifier
                    .padding(top = spacing_2)
                    .padding(horizontal = spacing_3),
                subtitle = "Ingredients",
                value = foodProduct.ingredients.orEmpty()
            )
            InformationSection(
                modifier = Modifier
                    .padding(top = spacing_2)
                    .padding(horizontal = spacing_3),
                subtitle = "Categories",
                value = foodProduct.categories.orEmpty()
            )
        }
    }
}

@Composable
fun NutrimentItem(modifier: Modifier, nutriment: Nutriment) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = nutriment.type.iconRes),
            contentDescription = nutriment.type.label,
            modifier = Modifier.size(large_icon_size)
        )
        Column(
            modifier = Modifier.padding(horizontal = spacing_1)
        ) {
            Text(text = nutriment.type.label, style = MaterialTheme.typography.bodySmall, maxLines = 1)
            Text(text = nutriment.value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun InformationSection(
    modifier: Modifier = Modifier,
    subtitle: String,
    value: String
) {
    Column(
        modifier = modifier,
    ) {
        Text(text = subtitle, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text(modifier = Modifier.padding(top = spacing_1), text = value, style = MaterialTheme.typography.bodyMedium)
    }
}
