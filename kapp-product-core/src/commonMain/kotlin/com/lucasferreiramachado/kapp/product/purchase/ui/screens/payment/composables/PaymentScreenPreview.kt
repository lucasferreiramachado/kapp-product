package com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.composables

import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.product.di.preview.previewPurchaseProduct
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.PaymentUiEvent
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.PaymentUiState
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.PaymentViewModel
import org.koin.compose.KoinApplicationPreview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun previewPaymentScreen(
    state: PaymentUiState,
    events: List<PaymentUiEvent>
) {
    KoinApplicationPreview(
        application = { modules(previewPurchaseProduct) }
    ) {
        val viewModel = koinViewModel<PaymentViewModel> {
            parametersOf(state)
        }
        events.forEach { event -> viewModel.onEvent(event) }
        PaymentScreen(viewModel)
    }
}