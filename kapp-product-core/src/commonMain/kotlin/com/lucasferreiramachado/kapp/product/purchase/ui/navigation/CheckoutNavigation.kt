package com.lucasferreiramachado.kapp.product.purchase.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.composables.Checkout

fun NavGraphBuilder.checkoutNavigation(
    coordinator: PurchaseProductCoordinator,
) {
    composable<PurchaseProductNavigationRoute.Checkout> {
        val viewModel = CheckoutViewModel(
            coordinator = coordinator,
            getCurrentPurchaseUseCase = coordinator.factory.getCurrentPurchaseUseCase
        )
        Checkout(viewModel)
    }
}