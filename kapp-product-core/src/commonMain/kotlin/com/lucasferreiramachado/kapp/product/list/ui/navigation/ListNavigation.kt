package com.lucasferreiramachado.kapp.product.list.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListUiState
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListViewModel
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.composables.ListScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.listNavigation() {
    composable<ProductListNavigationRoute.List> {
        val viewModel = koinViewModel<ListViewModel>(
            parameters = { parametersOf(ListUiState()) }
        )
        ListScreen(viewModel)
    }
}