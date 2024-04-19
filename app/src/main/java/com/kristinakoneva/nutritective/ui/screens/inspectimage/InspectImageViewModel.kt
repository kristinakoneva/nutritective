package com.kristinakoneva.nutritective.ui.screens.inspectimage

import android.net.Uri
import android.util.Log
import com.kristinakoneva.nutritective.domain.fooditems.FoodItemsRepository
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

@HiltViewModel
class InspectImageViewModel @Inject constructor(
    private val foodItemsRepository: FoodItemsRepository
) : BaseViewModel<InspectImageState, Unit>(InspectImageState()) {

    fun setUri(uri: Uri, fileName: String = "", imagePath: String = "") {
        analyzeImage(fileName, imagePath, uri)
    }

    private fun analyzeImage(fileName: String, imagePath: String, uri: Uri) {
        launchWithLoading {
            val requestBody =
                MultipartBody.Part.createFormData(
                    "image",
                    "$fileName.jpeg",
                    File(imagePath).asRequestBody("multipart/form-data".toMediaType())
                )
            try {
                val result = foodItemsRepository.getNutritionFromImage(requestBody)
                viewState = InspectImageState(uri, result)
            } catch (e: Exception) {
                Log.e("InspectImageViewModel", "analyzeImage: $e")
            }
        }
    }

    fun onFoodItemClicked(foodItem: FoodItem) {
        viewState = viewState.copy(selectedFoodItem = foodItem)
    }

    fun clearFoodItemSelection() {
        viewState = viewState.copy(selectedFoodItem = null)
    }
}
