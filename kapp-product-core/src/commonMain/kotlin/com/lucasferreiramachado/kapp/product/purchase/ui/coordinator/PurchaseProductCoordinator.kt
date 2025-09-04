package com.lucasferreiramachado.kapp.product.purchase.ui.coordinator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.StarNewPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.ui.navigation.PurchaseProductNavigationRoute
import com.lucasferreiramachado.kapp.product.purchase.ui.navigation.addressNavigation
import com.lucasferreiramachado.kapp.product.purchase.ui.navigation.checkoutNavigation
import com.lucasferreiramachado.kapp.product.purchase.ui.navigation.paymentNavigation
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator

class PurchaseProductCoordinator(
    override val parent: KCoordinator<*>,
    val startNewPurchaseUseCase: StarNewPurchaseUseCase,
) : ComposeKCoordinator<PurchaseProductCoordinatorAction> {

    private var navHostController: NavHostController? = null

    override fun handle(action: PurchaseProductCoordinatorAction) {
        when (action) {
            is PurchaseProductCoordinatorAction.ShowAddress -> {
                navHostController?.navigate(PurchaseProductNavigationRoute.Address)
            }
            PurchaseProductCoordinatorAction.ShowPayment -> {
                navHostController?.navigate(PurchaseProductNavigationRoute.Payment)
            }
            is PurchaseProductCoordinatorAction.ShowCheckout -> {
                navHostController?.navigate(PurchaseProductNavigationRoute.Checkout)
            }
            is PurchaseProductCoordinatorAction.GoBack -> {
                navHostController?.popBackStack()
            }
            is PurchaseProductCoordinatorAction.PurchaseRealized -> {
                val firstRoute = PurchaseProductNavigationRoute.Address
                navHostController?.popBackStack(
                    route = firstRoute,
                    inclusive = true
                )
            }
            is PurchaseProductCoordinatorAction.StartPurchase -> {
                startNewPurchaseUseCase.execute(
                    action.product
                )
                handle(PurchaseProductCoordinatorAction.ShowAddress)
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.apply {
            addressNavigation()
            paymentNavigation()
            checkoutNavigation()
        }
    }
}