package com.lucasferreiramachado.kapp.product.purchase.ui.screens.address

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchaseAddressUseCase
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinatorAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddressViewModel(
    initialState: AddressUiState = AddressUiState(),
    val coordinator: PurchaseProductCoordinator,
    val setPurchaseAddressUseCase: SetPurchaseAddressUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<AddressUiState> = _state.asStateFlow()
    
    fun onEvent(event: AddressUiEvent) {
        when (event) {
            is AddressUiEvent.BackButtonPressed -> {
                coordinator.trigger(PurchaseProductCoordinatorAction.GoBack)
            }
            is AddressUiEvent.AddressSelected -> {
                setPurchaseAddressUseCase.execute(event.address)
                coordinator.trigger(PurchaseProductCoordinatorAction.ShowPayment)
            }
        }
    }
}

