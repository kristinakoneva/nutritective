package com.kristinakoneva.nutritective.ui.screens.inspectimage

import android.Manifest
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kristinakoneva.nutritective.BuildConfig
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.AllergenStatusCard
import com.kristinakoneva.nutritective.ui.shared.composables.FoodItemCard
import com.kristinakoneva.nutritective.ui.shared.composables.FoodItemDetailsDialog
import com.kristinakoneva.nutritective.ui.shared.composables.ImagePickerBottomSheet
import com.kristinakoneva.nutritective.ui.shared.composables.InstructionStep
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus
import com.kristinakoneva.nutritective.ui.shared.utils.InstructionSteps
import com.kristinakoneva.nutritective.ui.theme.spacing_2
import com.kristinakoneva.nutritective.ui.theme.spacing_3
import com.kristinakoneva.nutritective.ui.theme.spacing_8
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

@Composable
fun InspectImageScreen(
    viewModel: InspectImageViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) { state ->
        InspectImageScreenContent(
            state.uri,
            state.foodItems,
            state.selectedFoodItem,
            state.allergenStatus,
            state.detectedAllergens,
            viewModel::setUri,
            viewModel::onFoodItemClicked,
            viewModel::clearFoodItemSelection
        )
    }
}

@Composable
fun InspectImageScreenContent(
    uri: Uri? = null,
    foodItems: List<FoodItem>? = null,
    selectedFoodItem: FoodItem? = null,
    allergenStatus: AllergenStatus? = null,
    detectedAllergens: List<String>? = null,
    onSetUri: (Uri, String, String) -> Unit,
    onFoodItemClicked: (FoodItem) -> Unit,
    clearFoodItemSelection: () -> Unit
) {
    val context = LocalContext.current
    val tempUri = remember { mutableStateOf<Uri?>(null) }
    fun getTempUri(): Uri? {

        val tmpFile = File.createTempFile("tmp_image_file", ".jpeg").apply {
            createNewFile()
            deleteOnExit()
        }

        return FileProvider.getUriForFile(context, "${BuildConfig.APPLICATION_ID}.provider", tmpFile)
    }

    fun getBitmapFromUri(context: Context, uri: Uri): Bitmap {
        return ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
    }

    fun saveToInternalStorage(bitmap: Bitmap, fileName: String): String? {

        val wrapper = ContextWrapper(context.applicationContext)
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)
        file = File(file, fileName)

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file.absolutePath
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            it?.let {
                val bitmap = getBitmapFromUri(context, it)
                val fileName = "image_${System.currentTimeMillis()}.jpeg"
                val ppPath = saveToInternalStorage(bitmap, fileName)
                onSetUri.invoke(it, fileName, ppPath!!)
            }
        }
    )

    val takePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { _ ->
            tempUri.value?.let {
                val bitmap = getBitmapFromUri(context, it)
                val fileName = "image_${System.currentTimeMillis()}.jpeg"
                val ppPath = saveToInternalStorage(bitmap, fileName)
                onSetUri.invoke(it, fileName, ppPath!!)
            }
        }
    )

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted, proceed to step 2
            val tmpUri = getTempUri()
            tempUri.value = tmpUri
            tmpUri?.let {
                takePhotoLauncher.launch(it)
            }
        } else {
            // Permission is denied, handle it accordingly
        }
    }

    var showBottomSheet by remember { mutableStateOf(false) }
    if (showBottomSheet) {
        ImagePickerBottomSheet(
            onDismiss = {
                showBottomSheet = false
            },
            onTakePhotoClick = {
                showBottomSheet = false

                val permission = Manifest.permission.CAMERA
                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
                ) {
                    // Permission is already granted, proceed to step 2
                    val tmpUri = getTempUri()
                    tempUri.value = tmpUri
                    tmpUri?.let {
                        takePhotoLauncher.launch(it)
                    }
                } else {
                    // Permission is not granted, request it
                    cameraPermissionLauncher.launch(permission)
                }
            },
            onPhotoGalleryClick = {
                showBottomSheet = false
                imagePicker.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(spacing_3)
            .padding(bottom = spacing_8)
    ) {
        if (selectedFoodItem != null) {
            Spacer(modifier = Modifier.padding(top = spacing_3))
            FoodItemDetailsDialog(foodItem = selectedFoodItem, onClose = clearFoodItemSelection)
        }

        Text(
            text = "Inspect image for food items",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = spacing_3),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    showBottomSheet = true
                }
            ) {
                Text(text = "Select image / Take photo")
            }
        }

        // preview image
        uri?.let {
            Text(
                text = "Inspected image:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing_3),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = it,
                modifier = Modifier
                    .padding(vertical = spacing_2)
                    .size(
                        160.dp
                    ),
                contentDescription = "Inspected image preview",
                alignment = Alignment.TopStart
            )
        }

        if (allergenStatus != null && !foodItems.isNullOrEmpty()) {
            Column {
                AllergenStatusCard(allergenStatus = allergenStatus, detectedAllergens = detectedAllergens)
            }
        }
        foodItems?.forEach {
            Spacer(modifier = Modifier.padding(top = spacing_2))
            FoodItemCard(foodItem = it, onClickAction = onFoodItemClicked)
        }
        if (foodItems.isNullOrEmpty() && uri != null) {
            Text(
                text = "No food items found.",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = spacing_2)
            )
        }

        if (foodItems == null) {
            Text(
                modifier = Modifier
                    .padding(top = spacing_3)
                    .fillMaxSize(),
                text = "Instructions",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(top = spacing_3))
            InspectImageInstructionSteps()
        }
    }
}

@Composable
fun InspectImageInstructionSteps() {
    Column {
        InstructionSteps.getInspectImageInstructionSteps().forEach { step ->
            InstructionStep(
                imageResId = step.imageResId,
                description = step.description
            )
            Spacer(modifier = Modifier.padding(top = spacing_2))
        }
    }
}
