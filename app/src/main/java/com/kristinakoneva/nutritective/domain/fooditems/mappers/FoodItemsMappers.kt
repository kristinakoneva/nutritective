package com.kristinakoneva.nutritective.domain.fooditems.mappers

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.FoodItemResource
import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.FoodItemsNutritionDataResponse
import com.kristinakoneva.nutritective.domain.fooditems.models.FoodItem
import com.kristinakoneva.nutritective.domain.shared.models.Nutriment
import com.kristinakoneva.nutritective.domain.shared.models.NutrimentType

fun FoodItemsNutritionDataResponse.toFoodItemsList(): List<FoodItem> = this.foodItems.map { it.toFoodItem() }.distinctBy { it.name }

fun FoodItemResource.toFoodItem() = FoodItem(
    name = this.name,
    calories = this.calories.toString().withCaloriesSuffix(),
    servingSize = this.servingSizeG.toString().withGramsSuffix(),
    nutriments = getNutrimentsListFromFoodItemResource(this)
)

fun getNutrimentsListFromFoodItemResource(foodItemResource: FoodItemResource): List<Nutriment> {
    val nutrimentsList = mutableListOf<Nutriment>()

    nutrimentsList.add(Nutriment(NutrimentType.FAT, foodItemResource.fatTotalG.toString().withGramsSuffix()))
    nutrimentsList.add(Nutriment(NutrimentType.SATURATED_FAT, foodItemResource.fatSaturatedG.toString().withGramsSuffix()))
    nutrimentsList.add(Nutriment(NutrimentType.CARBOHYDRATES, foodItemResource.carbohydratesTotalG.toString().withGramsSuffix()))
    nutrimentsList.add(Nutriment(NutrimentType.PROTEINS, foodItemResource.proteinG.toString().withGramsSuffix()))
    nutrimentsList.add(Nutriment(NutrimentType.SODIUM, foodItemResource.sodiumMg.toString().withMilligramsSuffix()))
    nutrimentsList.add(Nutriment(NutrimentType.POTASSIUM, foodItemResource.potassiumMg.toString().withMilligramsSuffix()))
    nutrimentsList.add(Nutriment(NutrimentType.CHOLESTEROL, foodItemResource.cholesterolMg.toString().withMilligramsSuffix()))
    nutrimentsList.add(Nutriment(NutrimentType.SUGARS, foodItemResource.sugarG.toString().withGramsSuffix()))
    nutrimentsList.add(Nutriment(NutrimentType.FIBER, foodItemResource.fiberG.toString().withGramsSuffix()))

    return nutrimentsList
}

fun String.withGramsSuffix() = this + "g"

fun String.withCaloriesSuffix() = this + "cal"

fun String.withMilligramsSuffix() = this + "mg"
