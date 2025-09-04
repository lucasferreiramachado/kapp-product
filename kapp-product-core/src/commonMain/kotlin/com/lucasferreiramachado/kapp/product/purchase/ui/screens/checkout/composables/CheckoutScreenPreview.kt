package com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.composables

import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.product.di.preview.previewPurchaseProduct
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutUiEvent
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutUiState
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutViewModel
import org.koin.compose.KoinApplicationPreview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun previewCheckoutScreen(
    state: CheckoutUiState,
    events: List<CheckoutUiEvent>
) {
    KoinApplicationPreview(
        application = { modules(previewPurchaseProduct) }
    ) {
        val viewModel = koinViewModel<CheckoutViewModel> {
            parametersOf(state)
        }
        events.forEach { event -> viewModel.onEvent(event) }
        CheckoutScreen(viewModel)
    }
}