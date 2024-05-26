package com.kristinakoneva.nutritective.ui.screens.usersettings

import android.annotation.SuppressLint
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.screens.usersettings.composables.SettingsCell
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.AllergenListedItem
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_primary
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_8
import com.kristinakoneva.nutritective.utils.Constants.BASE_URL_ALLERGEN_PRODUCTS

@Composable
fun UserSettingsScreen(
    viewModel: UserSettingsViewModel = hiltViewModel(),
    onNavigateToSelectAllergens: () -> Unit
) {
    BaseScreen(viewModel = viewModel, eventHandler = { event ->
        when (event) {
            is UserSettingsEvent.NavigateToSelectAllergens -> onNavigateToSelectAllergens()
        }
    }) { state ->
        when (state) {
            is UserSettingsState.Initial -> Unit
            is UserSettingsState.Content -> {
                UserSettingsScreenContent(
                    name = state.name,
                    allergens = state.allergens,
                    onNavigateToSelectAllergens = viewModel::onNavigateToSelectAllergens,
                    onCloseButtonClicked = {}
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingsScreenContent(
    name: String,
    allergens: List<String>,
    onNavigateToSelectAllergens: () -> Unit,
    onCloseButtonClicked: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = name,
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
                .padding(top = spacing_8, bottom = spacing_2)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.padding(spacing_3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.mipmap.ic_launcher), contentDescription = "Nutritective logo")
                Column(modifier = Modifier.padding(start = spacing_3)) {
                    Text(
                        text = "Nutritective",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Your Nutrition Detective",
                        style = MaterialTheme.typography.titleLarge,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Column(modifier = Modifier.padding(spacing_3)) {
                SettingsCell(text = "What do we detect?") {

                }
                Spacer(modifier = Modifier.padding(top = spacing_3))
                SettingsCell(text = "Select allergens", onClick = onNavigateToSelectAllergens)
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = spacing_1, vertical = spacing_2),
                color = md_theme_dark_primary
            )

            Text(
                text = "Your selected allergens:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(spacing_3),
                fontWeight = FontWeight.Bold
            )
            if (allergens.isEmpty()) {
                Text(text = "You haven't selected any allergens you would like Nutritective to detect.")
            } else {
                Column(modifier = Modifier.padding(horizontal = spacing_3)) {
                    allergens.forEach {
                        AllergenListedItem(name = it) {
                            val intent = CustomTabsIntent.Builder().build()
                            intent.launchUrl(
                                context,
                                Uri.parse(BASE_URL_ALLERGEN_PRODUCTS + it)
                            )
                        }
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = spacing_1))
                    Spacer(modifier = Modifier.padding(top = spacing_1))
                }
            }
        }
    }
}
