package com.kristinakoneva.nutritective.domain.recipes.models

data class Recipe(
    val title: String,
    val ingredients: List<String>,
    val servings: String,
    val instructions: List<String>
)
