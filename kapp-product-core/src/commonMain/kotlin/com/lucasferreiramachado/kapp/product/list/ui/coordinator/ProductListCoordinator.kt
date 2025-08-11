package com.lucasferreiramachado.kapp.product.list.ui.coordinator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kapp.data.product.model.Product
import com.lucasferreiramachado.kapp.data.purchase.model.ShoppingCartProduct
import com.lucasferreiramachado.kapp.product.ProductsCoordinatorAction
import com.lucasferreiramachado.kapp.product.list.ui.navigation.ProductListNavigationRoute
import com.lucasferreiramachado.kapp.product.list.ui.navigation.detailNavigation
import com.lucasferreiramachado.kapp.product.list.ui.navigation.listNavigation
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator

class ProductListCoordinator(
    val factory: ProductListCoordinatorFactoryI,
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<ProductListCoordinatorAction> {

    private var selectedProduct: Product? = null
    private var navHostController: NavHostController? = null

    override fun handle(action: ProductListCoordinatorAction) {
        when (action) {
            is ProductListCoordinatorAction.ShowList -> {
                navHostController?.navigate(ProductListNavigationRoute.List)
            }
            is ProductListCoordinatorAction.ShowDetail -> {
                selectedProduct = action.product
                navHostController?.navigate(ProductListNavigationRoute.Detail(
                    id = action.product.id,
                    name = action.product.name,
                    price = action.product.price,
                ))
            }
            is ProductListCoordinatorAction.GoBack -> {
                navHostController?.popBackStack()
            }

            is ProductListCoordinatorAction.StarBuyProductFlow -> {
                navHostController?.popBackStack()
                selectedProduct?.let {
                    parent.trigger(ProductsCoordinatorAction.StartPurchaseProduct(
                        ShoppingCartProduct(
                            id = it.id,
                            name = it.name,
                            price = it.price
                        )
                    ))
                }
            }
        }
    }
    override fun  setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.apply {
            listNavigation(this@ProductListCoordinator)
            detailNavigation(this@ProductListCoordinator)
        }
    }
}