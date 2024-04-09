package com.kristinakoneva.nutritective.ui.screens.foodproductdetails

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import com.kristinakoneva.nutritective.domain.foodproducts.models.Nutriment
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.theme.spacing_3

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodProductDetailsScreenContent(
    foodProduct: FoodProduct,
    onCloseButtonClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Food Product Details") },
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
                .padding(spacing_3)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(model = foodProduct.imageUrl, contentDescription = "Food Product Image")
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(text = foodProduct.name ?: "", fontSize = 20.sp)
                    Text(text = foodProduct.brands ?: "", fontSize = 16.sp)
                    //AsyncImage(model = foodProduct.nutriscoreUrl, contentDescription = "Nutri-Score Grade Image")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow {
                val nutriments = foodProduct.nutriments ?: emptyList()
                val chunkedNutriments = nutriments.chunked(2)
                items(chunkedNutriments) { chunkedNutriment ->
                    Row {
                        chunkedNutriment.forEach { nutriment ->
                            NutrimentItem(nutriment)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Allergens: ${foodProduct.allergens?.joinToString(", ") ?: ""}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Ingredients: ${foodProduct.ingredients ?: ""}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Categories: ${foodProduct.categories ?: ""}", fontSize = 16.sp)
        }
    }
}

@Composable
fun NutrimentItem(nutriment: Nutriment) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .height(60.dp)
            .background(color = Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = nutriment.type.label,
            modifier = Modifier.size(40.dp)
        )
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(text = nutriment.type.label, fontSize = 16.sp)
            Text(text = nutriment.value, fontSize = 14.sp)
        }
    }
}
