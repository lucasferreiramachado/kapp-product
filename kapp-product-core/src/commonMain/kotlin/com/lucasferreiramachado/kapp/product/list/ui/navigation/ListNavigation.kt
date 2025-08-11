package com.lucasferreiramachado.kapp.product.list.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinator
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListViewModel
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.composables.ListScreen

fun NavGraphBuilder.listNavigation(
    coordinator: ProductListCoordinator,
) {
    composable<ProductListNavigationRoute.List> {
        val viewModel = ListViewModel(
            coordinator = coordinator,
            getProductsUseCase = coordinator.factory.getProductsUseCase
        )
        ListScreen(viewModel)
    }
}