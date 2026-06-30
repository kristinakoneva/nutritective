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
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.screens.usersettings.composables.NameChangeDialog
import com.kristinakoneva.nutritective.ui.screens.usersettings.composables.SettingsCell
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.screens.usersettings.composables.AllergenListedItem
import com.kristinakoneva.nutritective.ui.shared.accessibility.sectionHeading
import com.kristinakoneva.nutritective.ui.shared.accessibility.semanticSection
import com.kristinakoneva.nutritective.ui.theme.NutritectiveDialogDefaults
import com.kristinakoneva.nutritective.ui.theme.md_theme_dark_primary
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_8
import com.kristinakoneva.nutritective.utils.Constants.BASE_URL_ALLERGEN_PRODUCTS

@Composable
fun UserSettingsScreen(
    viewModel: UserSettingsViewModel = hiltViewModel(),
    onNavigateToDetectionInfo: () -> Unit,
    onNavigateToSelectAllergens: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToAuth: () -> Unit
) {
    BaseScreen(viewModel = viewModel, eventHandler = { event ->
        when (event) {
            is UserSettingsEvent.NavigateToSelectAllergens -> onNavigateToSelectAllergens()
            is UserSettingsEvent.NavigateToDetectionInfo -> onNavigateToDetectionInfo()
            is UserSettingsEvent.NavigateBack -> onNavigateBack()
            is UserSettingsEvent.NavigateToAuth -> onNavigateToAuth()
        }
    }) { state ->
        UserSettingsScreenContent(
            name = state.name.orEmpty(),
            allergens = state.allergens,
            shouldShowLogoutConfirmationDialog = state.showLogoutConfirmationDialog,
            shouldShowNameChangeDialog = state.showNameChangeDialog,
            onNavigateToDetectionInfo = viewModel::onNavigateToDetectionInfo,
            onNavigateToSelectAllergens = viewModel::onNavigateToSelectAllergens,
            refreshAllergensList = viewModel::refreshAllergensList,
            onCloseButtonClicked = viewModel::onNavigateBack,
            onRemoveAllergenClicked = viewModel::onRemoveAllergenClicked,
            onEditButtonClicked = viewModel::onEditButtonClicked,
            onNameChangeConfirmed = viewModel::onNameChangeConfirmed,
            onNameChangeCancelled = viewModel::onNameChangeCancelled,
            onLogoutButtonClicked = viewModel::onLogoutButtonClicked,
            onLogoutConfirmed = viewModel::onLogoutConfirmed,
            onLogoutCancelled = viewModel::onLogoutCancelled
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingsScreenContent(
    name: String,
    allergens: List<String>,
    shouldShowLogoutConfirmationDialog: Boolean,
    shouldShowNameChangeDialog: Boolean,
    onNavigateToDetectionInfo: () -> Unit,
    onNavigateToSelectAllergens: () -> Unit,
    refreshAllergensList: () -> Unit,
    onCloseButtonClicked: () -> Unit,
    onRemoveAllergenClicked: (String) -> Unit,
    onEditButtonClicked: () -> Unit,
    onNameChangeConfirmed: (String) -> Unit,
    onNameChangeCancelled: () -> Unit,
    onLogoutButtonClicked: () -> Unit,
    onLogoutConfirmed: () -> Unit,
    onLogoutCancelled: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        refreshAllergensList()
    }

    if (shouldShowLogoutConfirmationDialog) {
        AlertDialog(
            onDismissRequest = onLogoutCancelled,
            containerColor = NutritectiveDialogDefaults.containerColor,
            titleContentColor = NutritectiveDialogDefaults.titleContentColor,
            textContentColor = NutritectiveDialogDefaults.textContentColor,
            confirmButton = {
                Button(onClick = onLogoutConfirmed) {
                    Text(text = stringResource(R.string.logout))
                }
            },
            dismissButton = {
                Button(onClick = onLogoutCancelled) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            text = { Text(stringResource(R.string.logout_confirmation_message)) },
            title = { Text(stringResource(R.string.logout)) }
        )
    }

    if (shouldShowNameChangeDialog) {
        NameChangeDialog(
            currentName = name,
            onConfirm = onNameChangeConfirmed,
            onCancel = onNameChangeCancelled
        )
    }

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
                        Icon(imageVector = Icons.Default.Close, contentDescription = stringResource(R.string.cd_close))
                    }
                },
                actions = {
                    IconButton(onClick = onEditButtonClicked) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(R.string.cd_edit_name)
                        )
                    }
                    IconButton(onClick = onLogoutButtonClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = stringResource(R.string.cd_logout)
                        )
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
            Column(modifier = Modifier.semanticSection(0f)) {
            Row(
                modifier = Modifier.padding(spacing_3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher),
                    contentDescription = stringResource(R.string.cd_app_logo),
                )
                Column(modifier = Modifier.padding(start = spacing_3)) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(R.string.app_tagline),
                        style = MaterialTheme.typography.titleLarge,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            }

            Column(modifier = Modifier.semanticSection(1f)) {
            Column(modifier = Modifier.padding(spacing_3)) {
                SettingsCell(text = stringResource(R.string.settings_what_do_we_detect), onClick = onNavigateToDetectionInfo)
                Spacer(modifier = Modifier.padding(top = spacing_3))
                SettingsCell(text = stringResource(R.string.select_allergens_title), onClick = onNavigateToSelectAllergens)
            }
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = spacing_1, vertical = spacing_2),
                color = md_theme_dark_primary
            )

            Column(modifier = Modifier.semanticSection(2f)) {
            Text(
                text = stringResource(R.string.your_selected_allergens_label),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(spacing_3)
                    .sectionHeading(),
                fontWeight = FontWeight.Bold
            )
            if (allergens.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_allergens_selected_message),
                    modifier = Modifier.padding(horizontal = spacing_3)
                )
            } else {
                Column(modifier = Modifier.padding(horizontal = spacing_3)) {
                    allergens.forEachIndexed { index, allergen ->
                        AllergenListedItem(
                            name = allergen,
                            onClick = {
                            val intent = CustomTabsIntent.Builder().build()
                            intent.launchUrl(
                                context,
                                Uri.parse(BASE_URL_ALLERGEN_PRODUCTS + allergen)
                            )
                        }, onRemoveAllergen = { onRemoveAllergenClicked(allergen) },
                            listIndex = index,
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = spacing_1))
                        Spacer(modifier = Modifier.padding(top = spacing_1))
                    }
                }
            }
            }
        }
    }
}
