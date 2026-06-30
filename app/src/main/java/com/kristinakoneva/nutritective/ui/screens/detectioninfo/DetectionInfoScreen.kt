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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.AllergenStatusCard
import com.kristinakoneva.nutritective.ui.shared.models.AllergenStatus
import com.kristinakoneva.nutritective.ui.shared.accessibility.sectionHeading
import com.kristinakoneva.nutritective.ui.shared.accessibility.semanticSection
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
                        text = stringResource(R.string.detection_info_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = stringResource(R.string.cd_navigate_back),
                            modifier = Modifier.size(large_icon_size)
                        )
                    }
                }
            )
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .padding(top = spacing_8)
                .padding(horizontal = spacing_3)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Column(modifier = Modifier.semanticSection(0f)) {
            Text(
                text = stringResource(R.string.detection_info_intro),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = spacing_2)
            )
            }

            Column(modifier = Modifier.semanticSection(1f)) {
            AllergenStatusCard(allergenStatus = AllergenStatus.SAFE)
            Text(
                text = stringResource(R.string.detection_info_safe_explanation),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = spacing_1),
                fontStyle = FontStyle.Italic
            )
            }

            Column(modifier = Modifier.semanticSection(2f)) {
            AllergenStatusCard(allergenStatus = AllergenStatus.WARNING)
            Text(
                text = stringResource(R.string.detection_info_warning_explanation),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = spacing_1),
                fontStyle = FontStyle.Italic
            )
            }

            Column(modifier = Modifier.semanticSection(3f)) {
            AllergenStatusCard(allergenStatus = AllergenStatus.DANGER, detectedAllergens = listOf("milk", "soy"))
            Text(
                text = stringResource(R.string.detection_info_danger_explanation),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = spacing_1),
                fontStyle = FontStyle.Italic
            )
            }

            Column(modifier = Modifier.semanticSection(4f)) {
            Text(
                text = stringResource(R.string.disclaimer_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(top = spacing_6)
                    .sectionHeading(),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.detection_info_disclaimer),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = spacing_2)
                    .padding(bottom = spacing_6)
            )
            }
        }
    }
}
