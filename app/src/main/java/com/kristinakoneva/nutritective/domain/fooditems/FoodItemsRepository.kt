package com.kristinakoneva.nutritective.domain.fooditems

import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import okhttp3.MultipartBody

interface FoodItemsRepository {

    suspend fun getNutritionFromText(inputText: String): List<FoodItem>

    suspend fun getNutritionFromImage(image: MultipartBody.Part): List<FoodItem>
}
