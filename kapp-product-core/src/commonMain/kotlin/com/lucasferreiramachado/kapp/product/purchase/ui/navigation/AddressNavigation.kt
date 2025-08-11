package com.lucasferreiramachado.kapp.product.purchase.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.AddressViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.composables.AddressScreen

fun NavGraphBuilder.addressNavigation(
    coordinator: PurchaseProductCoordinator,
) {
    composable<PurchaseProductNavigationRoute.Address> {
        val viewModel = AddressViewModel(
            coordinator = coordinator,
            setPurchaseAddressUseCase = coordinator.factory.setPurchaseAddressUseCase
        )
        AddressScreen(viewModel)
    }
}