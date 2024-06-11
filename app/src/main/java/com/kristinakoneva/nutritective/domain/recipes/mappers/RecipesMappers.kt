package com.kristinakoneva.nutritective.domain.recipes.mappers

import com.kristinakoneva.nutritective.data.remote.sources.edamam.models.RecipeResource
import com.kristinakoneva.nutritective.domain.recipes.models.Recipe

fun RecipeResource.toRecipe() = Recipe(
    title = this.label,
    imageUrl = this.image,
    source = this.source,
    url = this.url,
    ingredientsList = this.ingredientLines,
    cuisineType = this.cuisineType?.joinToString(", "),
    mealType = this.mealType?.joinToString(", "),
    dishType = this.dishType?.joinToString(", "),
)
