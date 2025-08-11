package com.lucasferreiramachado.kapp.product.compose.example.ui.coordinator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.auth.login.ui.coordinator.AuthCoordinatorAction
import com.lucasferreiramachado.kapp.product.ProductsCoordinatorAction
import com.lucasferreiramachado.kapp.product.compose.di.ExampleCoordinatorFactory
import com.lucasferreiramachado.kapp.product.compose.example.domain.model.ExampleItem
import com.lucasferreiramachado.kapp.product.compose.example.ui.navigation.ExampleNavigationRoute
import com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example.ExampleUiState
import com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example.ExampleViewModel
import com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example.composables.ExampleScreen
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator

class ExampleCoordinator(
    factory: ExampleCoordinatorFactoryI = ExampleCoordinatorFactory(),
    override val parent: KCoordinator<*>? = null
) : ComposeKCoordinator<ExampleCoordinatorAction> {

    private var navHostController: NavHostController? = null

    private var productsCoordinator = factory.productCoordinatorFactory.create(parent = this)
    private var authCoordinator = factory.authCoordinatorFactory.create(parent = this)

    override fun handle(action: ExampleCoordinatorAction) {
        when (action) {
            is ExampleCoordinatorAction.StartExample -> {
                navHostController?.navigate(ExampleNavigationRoute.ExampleScreen)
            }
            is ExampleCoordinatorAction.StartProductListFlow -> {
                authCoordinator.trigger(AuthCoordinatorAction.StartLogin {

                    productsCoordinator.trigger(ProductsCoordinatorAction.StartProductList)
                })
            }
        }
    }

    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
    ) {
        this.navHostController = navHostController

        authCoordinator.setupNavigation(navGraphBuilder, navHostController)
        productsCoordinator.setupNavigation(navGraphBuilder, navHostController)

        navGraphBuilder.composable<ExampleNavigationRoute.ExampleScreen> {
            val items = listOf(
                ExampleItem(
                    name = "Product List Flow",
                    action = ExampleCoordinatorAction.StartProductListFlow
                )
            )
            val initialState = ExampleUiState(items = items)
            val viewModel = ExampleViewModel(
                initialState,
                coordinator = this@ExampleCoordinator
            )
            ExampleScreen(viewModel)
        }
    }
}