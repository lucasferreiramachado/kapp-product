package com.lucasferreiramachado.kapp.product.purchase.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.AddressUiState
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.AddressViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.composables.AddressScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.addressNavigation() {
    composable<PurchaseProductNavigationRoute.Address> {
        val viewModel = koinViewModel<AddressViewModel>(
            parameters = { parametersOf(AddressUiState()) }
        )
        AddressScreen(viewModel)
    }
}