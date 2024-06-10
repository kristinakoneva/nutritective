package com.kristinakoneva.nutritective.domain.foodproducts.mappers

import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models.NutrimentsResource
import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.models.FoodProductResource
import com.kristinakoneva.nutritective.domain.foodproducts.models.FoodProduct
import com.kristinakoneva.nutritective.domain.shared.models.Nutriment
import com.kristinakoneva.nutritective.domain.shared.models.NutrimentType
import com.kristinakoneva.nutritective.extensions.roundToDecimalPlaces
import java.math.RoundingMode
import kotlin.math.roundToLong

fun FoodProductResource.toFoodProduct() = FoodProduct(
    name = this.productName,
    imageUrl = this.imageUrl,
    nutriments = this.nutriments?.toNutrimentsList(),
    ingredients = this.ingredientsText?.dropLast(1),
    allergens = this.allergensTags.toAllergensList(),
    brands = this.brands,
    categories = this.categories,
    nutriscoreUrl = this.nutriscoreGrade?.toNutriscoreGradeUrl(),
    hasAllergensInOtherLanguages = this.allergensTags.checkIfHasAllergensInOtherLanguages()
)

fun NutrimentsResource.toNutrimentsList(): List<Nutriment>? {
    val nutriments = mutableListOf<Nutriment>()

    if (this.energyValue != null && this.energyUnit != null) {
        nutriments.add(Nutriment(NutrimentType.ENERGY, combineValueWithUnit(this.energyValue, this.energyUnit)))
    }
    if (this.fatValue != null && this.fatUnit != null) {
        nutriments.add(Nutriment(NutrimentType.FAT, combineValueWithUnit(this.fatValue, this.fatUnit)))
    }
    if (this.saturatedFatValue != null && this.saturatedFatUnit != null) {
        nutriments.add(Nutriment(NutrimentType.SATURATED_FAT, combineValueWithUnit(this.saturatedFatValue, this.saturatedFatUnit)))
    }
    if (this.carbohydratesValue != null && this.carbohydratesUnit != null) {
        nutriments.add(Nutriment(NutrimentType.CARBOHYDRATES, combineValueWithUnit(this.carbohydratesValue, this.carbohydratesUnit)))
    }
    if (this.sugarsValue != null && this.sugarsUnit != null) {
        nutriments.add(Nutriment(NutrimentType.SUGARS, combineValueWithUnit(this.sugarsValue, this.sugarsUnit)))
    }
    if (this.fiberValue != null && this.fiberUnit != null) {
        nutriments.add(Nutriment(NutrimentType.FIBER, combineValueWithUnit(this.fiberValue, this.fiberUnit)))
    }
    if (this.proteinsValue != null && this.proteinsUnit != null) {
        nutriments.add(Nutriment(NutrimentType.PROTEINS, combineValueWithUnit(this.proteinsValue, this.proteinsUnit)))
    }
    if (this.saltValue != null && this.saltUnit != null) {
        nutriments.add(Nutriment(NutrimentType.SALT, combineValueWithUnit(this.saltValue, this.saltUnit)))
    }
    if (this.sodiumValue != null && this.sodiumUnit != null) {
        nutriments.add(Nutriment(NutrimentType.SODIUM, combineValueWithUnit(this.sodiumValue, this.sodiumUnit)))
    }

    if (nutriments.isEmpty()) return null
    return nutriments
}

private fun combineValueWithUnit(value: Double?, unit: String?): String = "${value?.roundToDecimalPlaces(2)} $unit"

fun String.toNutriscoreGradeUrl(): String? =
    if (this.isNotEmpty()) "https://static.openfoodfacts.org/images/misc/nutriscore-$this.svg" else null

fun List<String>?.toAllergensList(): List<String>? =
    this?.map { it.split(":")[1] }.takeIf { it?.isNotEmpty() == true }

fun List<String>?.checkIfHasAllergensInOtherLanguages(): Boolean =
    this?.any { !it.contains("en:") } == true
