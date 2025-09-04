package com.lucasferreiramachado.kapp.product.purchase.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutUiState
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.PaymentUiState
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.PaymentViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.composables.PaymentScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.paymentNavigation() {
    composable<PurchaseProductNavigationRoute.Payment> {
        val viewModel = koinViewModel<PaymentViewModel>(
            parameters = { parametersOf(PaymentUiState()) }
        )
        PaymentScreen(viewModel)
    }
}