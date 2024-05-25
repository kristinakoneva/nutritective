package com.kristinakoneva.nutritective.ui.shared.utils

import androidx.annotation.DrawableRes
import com.kristinakoneva.nutritective.R

enum class CommonAllergen(
    val text: String,
    @DrawableRes val icon: Int
) {
    MILK ("milk", R.drawable.ic_milk),
    EGGS ("eggs", R.drawable.ic_eggs),
    SESAME ("sesame", R.drawable.ic_sesame),
    FISH ("fish", R.drawable.ic_fish),
    SHELLFISH ("shellfish", R.drawable.ic_shellfish),
    TREE_NUTS ("tree nuts", R.drawable.ic_tree_nuts),
    PEANUTS ("peanuts", R.drawable.ic_peanuts),
    WHEAT ("wheat", R.drawable.ic_wheat),
    SOY ("soy", R.drawable.ic_soy)
}
