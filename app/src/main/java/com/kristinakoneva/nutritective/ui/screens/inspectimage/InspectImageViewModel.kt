package com.kristinakoneva.nutritective.ui.screens.inspectimage

import android.net.Uri
import android.util.Log
import com.kristinakoneva.nutritective.domain.fooditems.FoodItemsRepository
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.domain.session.SessionRepository
import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.extensions.detectAllergensPresence
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

@HiltViewModel
class InspectImageViewModel @Inject constructor(
    private val foodItemsRepository: FoodItemsRepository,
    private val userRepository: UserRepository
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
                val foodItems = foodItemsRepository.getNutritionFromImage(requestBody)
                val userAllergenList = userRepository.getUserAllergensList()
                if (userAllergenList.isNotEmpty()) {
                    val detectedAllergens: List<String> =
                        foodItems.map { foodItem -> foodItem.name }.detectAllergensPresence(userAllergenList)
                    val allergenStatus =
                        if (detectedAllergens.isEmpty()) {
                            AllergenStatus.WARNING
                        } else {
                            AllergenStatus.DANGER
                        }
                    viewState =
                        InspectImageState(
                            uri = uri,
                            foodItems = foodItems,
                            allergenStatus = allergenStatus,
                            detectedAllergens = detectedAllergens.distinct()
                        )
                } else {
                    viewState = InspectImageState(uri = uri, foodItems = foodItems)
                }
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

    fun onClearLastSearchClicked() {
        viewState = viewState.copy(showClearLastSearchDialog = true)
    }

    fun onClearLastSearchConfirmed() {
        viewState = InspectImageState(showClearLastSearchDialog = false)
    }

    fun onClearLastSearchCancelled() {
        viewState = viewState.copy(showClearLastSearchDialog = false)
    }
}
