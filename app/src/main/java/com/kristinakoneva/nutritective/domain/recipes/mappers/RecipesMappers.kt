package com.kristinakoneva.nutritective.domain.recipes.mappers

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.models.RecipeResource
import com.kristinakoneva.nutritective.domain.recipes.models.Recipe

fun RecipeResource.toRecipe() = Recipe(
    title = title,
    servings = servings,
    ingredients = ingredients.toIngredientList(),
    instructions = instructions.toInstructionsList()
)

fun String.toIngredientList(): List<String> {
    return this.split("|").map { it.replace(";", " -") }
}

fun String.toInstructionsList(): List<String> {
    return this.split(".")
}
