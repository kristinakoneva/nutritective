package com.kristinakoneva.nutritective.data.local.sharedprefs

import javax.inject.Inject
import android.content.SharedPreferences
import com.kristinakoneva.nutritective.di.qualifiers.SharedPrefs

class SharedPreferencesSourceImpl @Inject constructor(
    @SharedPrefs private val sharedPrefs: SharedPreferences
) : SharedPreferencesSource {
    companion object {
        const val KEY_LAST_SCANNED_BARCODE = "last_scanned_barcode"
    }

    override fun getLastScannedBarcode(): String? =
        sharedPrefs.getString(KEY_LAST_SCANNED_BARCODE, null)

    override fun saveLastScannedBarcode(barcode: String) =
        sharedPrefs.edit().putString(KEY_LAST_SCANNED_BARCODE, barcode).apply()

    override fun clearLastScannedBarcode() {
        sharedPrefs.edit().remove(KEY_LAST_SCANNED_BARCODE).apply()
    }
}
