package com.lucasferreiramachado.kapp.product.list.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailUiState
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailViewModel
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.composables.DetailScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.detailNavigation() {
    composable<ProductListNavigationRoute.Detail> {
        val route = it.toRoute<ProductListNavigationRoute.Detail>()
        val viewModel = koinViewModel<DetailViewModel>(
            parameters = { parametersOf(
                DetailUiState(
                    name = route.name,
                    price = "R$ ${route.price}"
                )
            ) }
        )
        DetailScreen(viewModel)
    }
}