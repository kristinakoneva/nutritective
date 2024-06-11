package com.kristinakoneva.nutritective.ui.screens.detectioninfo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.AllergenStatusCard
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus
import com.kristinakoneva.nutritective.ui.theme.large_icon_size
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_5
import com.kristinakoneva.nutritective.ui.theme.spacing_6
import com.kristinakoneva.nutritective.ui.theme.spacing_8

@Composable
fun DetectionInfoScreen(
    viewModel: DetectionInfoViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    BaseScreen(viewModel = viewModel, eventHandler = { event ->
        when (event) {
            DetectionInfoEvent.NavigateBack -> onNavigateBack()
        }
    }) {
        DetectionInfoScreenContent(
            onNavigateBack = viewModel::onNavigateBack
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetectionInfoScreenContent(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Detection info",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = "Back button",
                            modifier = Modifier.size(large_icon_size)
                        )
                    }
                }
            )
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .padding(top = spacing_8, bottom = spacing_2)
                .padding(horizontal = spacing_3)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "After you have selected the allergens which you would like to be detected, " +
                    "Nutritective will go through the ingredients and allergens of the scanned food products " +
                    "and provide you with allergen status information. \n\n\n" +
                    "The allergen status can be one of the following:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = spacing_2)
            )
            Spacer(modifier = Modifier.padding(top = spacing_3))
            AllergenStatusCard(allergenStatus = AllergenStatus.SAFE)
            Text(
                text = "The food product should be safe to consume since none of your selected allergens were detected.",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = spacing_1),
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.padding(top = spacing_5))
            AllergenStatusCard(allergenStatus = AllergenStatus.WARNING)
            Text(
                text = "Nutritective could not determine the allergen status because of lack of data for the food product/item or recipe.",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = spacing_1),
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.padding(top = spacing_5))
            AllergenStatusCard(allergenStatus = AllergenStatus.DANGER, detectedAllergens = listOf("milk", "soy"))
            Text(
                text = "Nutritective has detected some dangerous allergens and those allergens will be listed.",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = spacing_1),
                fontStyle = FontStyle.Italic
            )

            Text(
                text = "Disclaimer:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = spacing_6),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Our app provides information on allergens in food products to assist users in making informed decisions. However, please note that this information is intended for general informational purposes only and should not be relied upon as a substitute for professional advice or judgment.\n" +
                    "\n" +
                    "Due to the complexity and variability of food products, some allergens may be difficult to detect, and the database may contain incomplete or inaccurate information. The food product database we are using for finding products by barcode is open-source, which means that it is continuously updated by a community of contributors. While we strive for accuracy, we cannot guarantee the completeness or correctness of the data.\n" +
                    "\n" +
                    "Users are responsible for verifying the information provided and for any decisions made based on it. We do not accept any liability for inaccuracies or omissions, or for any consequences arising from the use of the app. Always consult with a healthcare professional or the product manufacturer for the most accurate and reliable information.\n" +
                    "\n" +
                    "By using this app, you acknowledge and agree to these terms.",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = spacing_2)
            )
        }
    }
}
