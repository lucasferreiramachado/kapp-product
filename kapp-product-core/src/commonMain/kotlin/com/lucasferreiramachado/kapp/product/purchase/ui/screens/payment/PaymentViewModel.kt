package com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kapp.data.purchase.model.PaymentMethod
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchasePaymentMethodUseCase
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaymentViewModel(
    initialState: PaymentUiState = PaymentUiState(),
    val coordinator: KCoordinator<PurchaseProductCoordinatorAction>? = null,
    val setPurchasePaymentMethodUseCase: SetPurchasePaymentMethodUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<PaymentUiState> = _state.asStateFlow()
    
    fun onEvent(event: PaymentUiEvent) {
        when (event) {
            is PaymentUiEvent.BackButtonPressed -> {
                coordinator?.trigger(PurchaseProductCoordinatorAction.GoBack)
            }
            is PaymentUiEvent.CreditCardSelected -> {
                setPurchasePaymentMethodUseCase.execute(paymentMethod = PaymentMethod.CreditCard)
                coordinator?.trigger(PurchaseProductCoordinatorAction.ShowCheckout)
            }
            is PaymentUiEvent.PixSelected -> {
                setPurchasePaymentMethodUseCase.execute(paymentMethod = PaymentMethod.Pix)
                coordinator?.trigger(PurchaseProductCoordinatorAction.ShowCheckout)
            }
        }
    }
}