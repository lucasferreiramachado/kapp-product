package com.lucasferreiramachado.kapp.product.purchase.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.PaymentViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.composables.PaymentScreen

fun NavGraphBuilder.paymentNavigation(
    coordinator: PurchaseProductCoordinator,
) {
    composable<PurchaseProductNavigationRoute.Payment> {
        val viewModel = PaymentViewModel(
            coordinator = coordinator,
            setPurchasePaymentMethodUseCase = coordinator.factory.setPurchasePaymentMethodUseCase,
        )
        PaymentScreen(viewModel)
    }
}