package com.kristinakoneva.nutritective.ui.screens.opencamera

import android.Manifest
import android.annotation.SuppressLint
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_8

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OpenCameraScreen(
    viewModel: OpenCameraViewModel = hiltViewModel(),
    onNavigateToFoodProductDetails: () -> Unit,
    onNavigateUp: () -> Unit
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
        }
    }) { state ->
        when (state) {
            is OpenCameraState.Initial -> {
                val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
                if (cameraPermissionState.status.isGranted) {
                    OpenCameraScreenContent(
                        onNavigateUp = {
                            cameraProviderFuture.get().unbindAll()
                            onNavigateUp()
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
                    onNavigateUp = onNavigateUp
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
    onNavigateUp: () -> Unit,
    onEnterBarcodeManuallyButtonClicked: () -> Unit,
    successfulBarcodeScannedListener: (String?) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Scan barcode") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back button")
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
                .padding(top = spacing_8, bottom = spacing_2),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = spacing_2),
                text = "Locate the barcode on the product and position it within the preview area."
            )
            CameraPreview(successfulBarcodeScannedListener)
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
    onNavigateUp: () -> Unit,
    onTryAnotherProductButtonClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Product not found")
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back button")
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
