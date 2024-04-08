package com.kristinakoneva.nutritective.data.local.sharedprefs

interface SharedPreferencesSource {

    fun getLastScannedBarcode(): String?

    fun saveLastScannedBarcode(barcode: String)
}
