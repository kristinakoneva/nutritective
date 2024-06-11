package com.kristinakoneva.nutritective.domain.recipes.models

data class Recipe(
    val title: String,
    val imageUrl: String,
    val source: String,
    val url: String,
    val ingredientsList: List<String>,
    val cuisineType: String,
    val mealType: String,
    val dishType: String
)
