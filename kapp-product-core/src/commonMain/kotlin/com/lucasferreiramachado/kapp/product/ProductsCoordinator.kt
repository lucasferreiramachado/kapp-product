package com.lucasferreiramachado.kapp.product

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kapp.data.product.model.Product
import com.lucasferreiramachado.kapp.data.purchase.model.ShoppingCartProduct
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinatorAction
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator

sealed class ProductsCoordinatorAction: KCoordinatorAction {
    data object StartProductList : ProductsCoordinatorAction()
    data class StartProductDetail(val product: Product) : ProductsCoordinatorAction()
    data class StartPurchaseProduct(val product: ShoppingCartProduct) : ProductsCoordinatorAction()
}

class ProductsCoordinator(
    factory: ProductsCoordinatorFactoryI,
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<ProductsCoordinatorAction> {
    private val purchaseProductCoordinator = factory.purchaseProductCoordinatorFactory.create(
        parent = this
    )
    private val productListCoordinator = factory.productListCoordinatorFactory.create(
        parent = this
    )

    private var navHostController: NavHostController? = null

    override fun handle(action: ProductsCoordinatorAction) {
        when (action) {
            is ProductsCoordinatorAction.StartPurchaseProduct -> {
                purchaseProductCoordinator.trigger(
                    PurchaseProductCoordinatorAction.StartPurchase(action.product)
                )
            }
            is ProductsCoordinatorAction.StartProductList -> {
                productListCoordinator.trigger(ProductListCoordinatorAction.ShowList)
            }
            is ProductsCoordinatorAction.StartProductDetail ->  {
                val item = action.product
                productListCoordinator.trigger(
                    ProductListCoordinatorAction.ShowDetail(
                        Product(
                            id = item.id,
                            name = item.name,
                            price = item.price
                        )
                    )
                )
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        purchaseProductCoordinator.setupNavigation(navGraphBuilder, navHostController)
        productListCoordinator.setupNavigation(navGraphBuilder, navHostController)
    }
}