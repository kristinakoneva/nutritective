package com.kristinakoneva.nutritective.ui.screens.opencamera

import android.Manifest
import android.annotation.SuppressLint
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.screens.opencamera.composables.BarcodeInputDialog
import com.kristinakoneva.nutritective.ui.screens.opencamera.composables.CameraPreview
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.theme.large_icon_size
import com.kristinakoneva.nutritective.ui.theme.spacing_10
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_4

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OpenCameraScreen(
    viewModel: OpenCameraViewModel = hiltViewModel(),
    onNavigateToFoodProductDetails: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        onDispose {
            cameraProviderFuture.get().unbindAll()
        }
    }

    BaseScreen(viewModel = viewModel, eventHandler = { event ->
        when (event) {
            is OpenCameraEvent.ProductFound -> {
                cameraProviderFuture.get().unbindAll()
                onNavigateToFoodProductDetails()
            }

            is OpenCameraEvent.NavigateBack -> onNavigateBack()
        }
    }) { state ->
        when (state) {
            is OpenCameraState.Initial -> {
                val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
                if (cameraPermissionState.status.isGranted) {
                    OpenCameraScreenContent(
                        onNavigateBack = {
                            cameraProviderFuture.get().unbindAll()
                            viewModel.onNavigateBack()
                        },
                        onEnterBarcodeManuallyButtonClicked = viewModel::onEnterBarcodeManuallyButtonClicked
                    ) { barcode ->
                        if (!barcode.isNullOrEmpty()) {
                            viewModel.onBarcodeScanned(barcode)
                        }
                    }
                } else if (cameraPermissionState.status.shouldShowRationale) {
                    Text("Camera Permission permanently denied")
                } else {
                    SideEffect {
                        cameraPermissionState.run { launchPermissionRequest() }
                    }
                    Text("No Camera Permission")
                }
            }

            is OpenCameraState.ProductNotFound -> {
                cameraProviderFuture.get().unbindAll()
                OpenCameraScreenProductNotFoundContent(
                    onTryAnotherProductButtonClicked = viewModel::onTryAnotherProductButtonClicked,
                    onNavigateBack = viewModel::onNavigateBack
                )
            }

            is OpenCameraState.ShowBarcodeInputDialog -> {
                cameraProviderFuture.get().unbindAll()
                BarcodeInputDialog(
                    onConfirm = { barcode ->
                        viewModel.onBarcodeScanned(barcode)
                    },
                    onCancel = viewModel::resetToInitialState
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenCameraScreenContent(
    onNavigateBack: () -> Unit,
    onEnterBarcodeManuallyButtonClicked: () -> Unit,
    successfulBarcodeScannedListener: (String?) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Scan barcode") },
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = spacing_3)
                .padding(top = spacing_10, bottom = spacing_2),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(bottom = spacing_2),
                text = "Locate the barcode on the product and position it within the preview area:",
                style = MaterialTheme.typography.titleMedium
            )
            CameraPreview(successfulBarcodeScannedListener)
            Text(
                modifier = Modifier.padding(top = spacing_4, bottom = spacing_2),
                text = "Alternatively:",
                style = MaterialTheme.typography.titleMedium
            )
            Button(modifier = Modifier.padding(bottom = spacing_3), onClick = onEnterBarcodeManuallyButtonClicked) {
                Text(text = "Enter barcode manually")
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenCameraScreenProductNotFoundContent(
    onNavigateBack: () -> Unit,
    onTryAnotherProductButtonClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Product not found")
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(spacing_3),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.illustration_not_found), contentDescription = "Product not found")
            Text(
                modifier = Modifier.padding(top = spacing_2),
                text = "Sorry, we couldn't find the product for the provided barcode.",
                textAlign = TextAlign.Center
            )
            Button(modifier = Modifier.padding(top = spacing_3), onClick = onTryAnotherProductButtonClicked) {
                Text(text = "Try another product")
            }
        }
    }
}
