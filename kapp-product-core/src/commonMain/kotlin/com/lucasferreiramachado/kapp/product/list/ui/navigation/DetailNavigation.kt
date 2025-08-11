package com.lucasferreiramachado.kapp.product.list.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinator
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailUiState
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailViewModel
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.composables.DetailScreen

fun NavGraphBuilder.detailNavigation(
    coordinator: ProductListCoordinator,
) {
    composable<ProductListNavigationRoute.Detail> {
        val route = it.toRoute<ProductListNavigationRoute.Detail>()
        val viewModel = DetailViewModel(
            initialState = DetailUiState(
                name = route.name,
                price = "R$ ${route.price}"
            ),
            coordinator = coordinator
        )
        DetailScreen(viewModel)
    }
}