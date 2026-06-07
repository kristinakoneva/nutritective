package com.kristinakoneva.nutritective.ui.shared.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kristinakoneva.nutritective.R

enum class CommonAllergen(
    val key: String,
    @StringRes val labelRes: Int,
    @DrawableRes val icon: Int
) {
    MILK("milk", R.string.allergen_milk, R.drawable.ic_milk),
    EGGS("eggs", R.string.allergen_eggs, R.drawable.ic_eggs),
    SESAME("sesame", R.string.allergen_sesame, R.drawable.ic_sesame),
    FISH("fish", R.string.allergen_fish, R.drawable.ic_fish),
    SHELLFISH("shellfish", R.string.allergen_shellfish, R.drawable.ic_shellfish),
    TREE_NUTS("tree nuts", R.string.allergen_tree_nuts, R.drawable.ic_tree_nuts),
    PEANUTS("peanuts", R.string.allergen_peanuts, R.drawable.ic_peanuts),
    WHEAT("wheat", R.string.allergen_wheat, R.drawable.ic_wheat),
    SOY("soy", R.string.allergen_soy, R.drawable.ic_soy)
}
