package com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.composables

import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.product.di.preview.previewPurchaseProduct
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.AddressUiEvent
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.AddressUiState
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.AddressViewModel
import org.koin.compose.KoinApplicationPreview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun previewAddressScreen(
    state: AddressUiState,
    events: List<AddressUiEvent>
) {
    KoinApplicationPreview(
        application = { modules(previewPurchaseProduct) }
    ) {
        val viewModel = koinViewModel<AddressViewModel> {
            parametersOf(state)
        }
        events.forEach { event -> viewModel.onEvent(event) }
        AddressScreen(viewModel)
    }
}