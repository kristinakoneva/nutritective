package com.kristinakoneva.nutritective.ui.screens.foodproductdetails

import com.kristinakoneva.nutritective.domain.foodproducts.FoodProductsRepository
import com.kristinakoneva.nutritective.domain.session.SessionRepository
import com.kristinakoneva.nutritective.domain.user.UserRepository
import com.kristinakoneva.nutritective.extensions.detectAllergensPresence
import com.kristinakoneva.nutritective.ui.shared.base.BaseViewModel
import com.kristinakoneva.nutritective.ui.shared.utils.AllergenStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodProductDetailsViewModel @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val foodProductRepository: FoodProductsRepository,
    private val userRepository: UserRepository
) : BaseViewModel<FoodProductDetailsState, FoodProductDetailsEvent>(FoodProductDetailsState.Initial) {

    init {
        launchWithLoading {
            val barcode = sessionRepository.getLastScannedFoodProductBarcode()!!
            val product = foodProductRepository.fetchFoodProductByBarcode(barcode)
            val userAllergenList = userRepository.getUserAllergensList()
            if (userAllergenList.isNotEmpty()) {
                var detectedAllergens: List<String>? = product?.allergens?.detectAllergensPresence(userAllergenList)
                detectedAllergens = if (detectedAllergens != null) {
                    detectedAllergens + product?.ingredients?.detectAllergensPresence(userAllergenList).orEmpty()
                } else {
                    product?.ingredients?.detectAllergensPresence(userAllergenList)
                }
                val allergenStatus =
                    if (detectedAllergens.isNullOrEmpty() && !product?.allergens.isNullOrEmpty() && !product!!.hasAllergensInOtherLanguages) {
                        AllergenStatus.SAFE
                    } else if ((detectedAllergens.isNullOrEmpty() && product?.allergens.isNullOrEmpty()) ||
                        (detectedAllergens.isNullOrEmpty() && product!!.hasAllergensInOtherLanguages)
                    ) {
                        AllergenStatus.WARNING
                    } else {
                        AllergenStatus.DANGER
                    }
                viewState = FoodProductDetailsState.Content(product!!, allergenStatus, detectedAllergens?.distinct())
            } else {
                viewState = FoodProductDetailsState.Content(product!!)
            }
        }
    }

    fun onCloseButtonClicked() {
        emitEvent(FoodProductDetailsEvent.OnNavigateToScanBarcode)
    }
}
