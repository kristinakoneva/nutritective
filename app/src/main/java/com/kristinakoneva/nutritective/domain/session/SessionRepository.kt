package com.kristinakoneva.nutritective.domain.session

interface SessionRepository {
    fun getLastScannedFoodProductBarcode(): String?

    fun saveLastScannedFoodProductBarcode(barcode: String)

    fun clearLastScannedFoodProductBarcode()
}
