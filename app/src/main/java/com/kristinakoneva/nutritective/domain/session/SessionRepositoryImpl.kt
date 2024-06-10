package com.kristinakoneva.nutritective.domain.session

import com.kristinakoneva.nutritective.data.local.sharedprefs.SharedPreferencesSource
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sharedPrefs: SharedPreferencesSource
) : SessionRepository {

    override fun getLastScannedFoodProductBarcode(): String? = sharedPrefs.getLastScannedBarcode()

    override fun saveLastScannedFoodProductBarcode(barcode: String) = sharedPrefs.saveLastScannedBarcode(barcode)

    override fun clearLastScannedFoodProductBarcode() = sharedPrefs.clearLastScannedBarcode()
}
