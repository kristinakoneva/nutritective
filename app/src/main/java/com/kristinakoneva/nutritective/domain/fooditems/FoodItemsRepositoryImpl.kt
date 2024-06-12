package com.kristinakoneva.nutritective.domain.fooditems

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.CalorieNinjasSource
import com.kristinakoneva.nutritective.domain.fooditems.mappers.toFoodItemsList
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class FoodItemsRepositoryImpl @Inject constructor(
    private val calorieNinjasSource: CalorieNinjasSource
) : FoodItemsRepository {
    override suspend fun getNutritionFromText(inputText: String): List<FoodItem> = withContext(Dispatchers.IO) {
        calorieNinjasSource.getNutritionFromText(inputText).toFoodItemsList()
    }

    override suspend fun getNutritionFromImage(image: MultipartBody.Part): List<FoodItem> = withContext(Dispatchers.IO) {
        calorieNinjasSource.getNutritionFromImage(image).toFoodItemsList()
    }

}
