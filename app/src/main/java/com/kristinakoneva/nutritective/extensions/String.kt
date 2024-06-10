package com.kristinakoneva.nutritective.extensions

fun String.isDigitsOnly(): Boolean {
    return all { it.isDigit() }
}

fun String.detectAllergensPresence(allergens: List<String>): List<String> = allergens.filter { allergen ->
    contains(allergen, ignoreCase = true) ||
        contains(allergen.dropLast(1), ignoreCase = true) ||
        containsAnyWordFrom(allergen)
}

fun List<String>.detectAllergensPresence(allergens: List<String>): List<String> = flatMap { it.detectAllergensPresence(allergens) }

fun String.containsAnyWordFrom(other: String): Boolean = split(" ").any { other.contains(it, ignoreCase = true) }
