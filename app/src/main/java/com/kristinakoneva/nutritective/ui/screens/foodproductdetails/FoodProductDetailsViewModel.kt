package com.kristinakoneva.nutritective.ui.screens.foodproductdetails

import com.kristinakoneva.nutritective.domain.foodproducts.FoodProductsRepository
import com.kristinakoneva.nutritective.domain.session.SessionRepository
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodProductDetailsViewModel @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val foodProductRepository: FoodProductsRepository
) : BaseViewModel<FoodProductDetailsState, Unit>(FoodProductDetailsState.Initial) {

    init {
        launchWithLoading {
            val barcode = sessionRepository.getLastScannedFoodProductBarcode()!!
            val product = foodProductRepository.fetchFoodProductByBarcode(barcode)
            viewState = FoodProductDetailsState.Content(product!!)
        }
    }
}
