package com.kristinakoneva.nutritective.ui.screens.opencamera

import android.Manifest
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.kristinakoneva.nutritective.R
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.theme.spacing_1
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.utils.BarcodeAnalyzer

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OpenCameraScreen(
    viewModel: OpenCameraViewModel = hiltViewModel(),
    onNavigateToFoodProductDetails: () -> Unit
) {
    val context = LocalContext.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
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
                    OpenCameraScreenContent {
                        if (!it.isNullOrEmpty()) {
                            viewModel.onBarcodeScanned(it)
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
                OpenCameraScreenProductNotFoundContent(onScanAgainButtonClicked = viewModel::onScanAgainButtonClicked)
            }
        }
    }
}

@Composable
fun OpenCameraScreenContent(
    successfulBarcodeScannedListener: (String?) -> Unit
) {
    val localContext = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(localContext)
    }
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val previewView = PreviewView(context)
            val preview = Preview.Builder().build()
            val selector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            preview.setSurfaceProvider(previewView.surfaceProvider)
            val imageAnalysis = ImageAnalysis.Builder().build()
            imageAnalysis.setAnalyzer(
                ContextCompat.getMainExecutor(context),
                BarcodeAnalyzer(successfulBarcodeScannedListener)
            )

            runCatching {
                cameraProviderFuture.get().unbindAll()
                cameraProviderFuture.get().bindToLifecycle(
                    lifecycleOwner,
                    selector,
                    preview,
                    imageAnalysis
                )
            }.onFailure {
                Log.e("CAMERA", "Camera bind error ${it.localizedMessage}", it)
            }
            previewView
        }
    )
}

@Composable
fun OpenCameraScreenProductNotFoundContent(
    onScanAgainButtonClicked: () -> Unit
) {
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
            text = "Sorry, we couldn't find the product you scanned. Please try again or scan another product.",
            textAlign = TextAlign.Center
        )
        Button(modifier = Modifier.padding(top = spacing_3), onClick = onScanAgainButtonClicked) {
            Text(text = "Scan again")
        }
    }
}
