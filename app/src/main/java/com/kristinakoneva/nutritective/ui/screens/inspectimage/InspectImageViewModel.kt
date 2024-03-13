package com.kristinakoneva.nutritective.ui.screens.inspectimage

import android.net.Uri
import android.util.Log
import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.CalorieNinjasSource
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

@HiltViewModel
class InspectImageViewModel @Inject constructor(
    private val source: CalorieNinjasSource
) : BaseViewModel<InspectImageState, Unit>(InspectImageState()) {

    fun setUri(uri: Uri, fileName: String = "", imagePath: String = "") {
        analyzeImage(fileName, imagePath, uri)
    }

    private fun analyzeImage(fileName: String, imagePath: String, uri: Uri) {
        launch {
            val requestBody =
                MultipartBody.Part.createFormData(
                    "image",
                    "$fileName.jpeg",
                    File(imagePath).asRequestBody("multipart/form-data".toMediaType())
                )

            Log.e("InspectImageViewModel", "analyzeImage: $requestBody")
            try {
                val result = source.getNutritionFromImage(requestBody).product.joinToString(", ")
                viewState = InspectImageState(uri, result)
            } catch (e: Exception) {
                Log.e("InspectImageViewModel", "analyzeImage: $e")
            }
        }
    }
}