package com.lucasferreiramachado.kapp.product.purchase.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutUiState
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.composables.Checkout
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.checkoutNavigation() {
    composable<PurchaseProductNavigationRoute.Checkout> {
        val viewModel = koinViewModel<CheckoutViewModel>(
            parameters = { parametersOf(CheckoutUiState()) }
        )
        Checkout(viewModel)
    }
}