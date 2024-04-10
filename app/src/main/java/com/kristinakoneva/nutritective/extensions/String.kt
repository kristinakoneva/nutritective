package com.kristinakoneva.nutritective.extensions

fun String.isDigitsOnly(): Boolean {
    return all { it.isDigit() }
}
