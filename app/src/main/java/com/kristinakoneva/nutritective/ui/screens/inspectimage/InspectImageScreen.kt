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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kristinakoneva.nutritective.BuildConfig
import com.kristinakoneva.nutritective.ui.shared.base.BaseScreen
import com.kristinakoneva.nutritective.ui.shared.composables.ImagePickerBottomSheet
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

@Composable
fun InspectImageScreen(
    viewModel: InspectImageViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel, eventHandler = {}) { state ->
        MyImageArea(state.uri, state.result) { it1, it2, it3 ->
            viewModel.setUri(it1, it2, it3)
        }
    }
}

@Composable
fun MyImageArea(
    uri: Uri? = null,
    result: String? = null,
    onSetUri: (Uri, String, String) -> Unit
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
            takePhotoLauncher.launch(tempUri.value)
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
                    takePhotoLauncher.launch(tempUri.value)
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
        modifier = Modifier.fillMaxWidth()
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    showBottomSheet = true
                }
            ) {
                Text(text = "Select / Take")
            }
        }

        //preview selfie
        uri?.let {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = it,
                    modifier = Modifier.size(
                        160.dp
                    ),
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(text = result ?: "No result yet")
    }
}