package com.kristinakoneva.nutritective.extensions

fun Double.roundToDecimalPlaces(decimalPlaces: Int): Double {
    require(decimalPlaces >= 0)
    var multiplier = 1.0
    repeat(decimalPlaces) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}
