package com.lucasferreiramachado.kapp.product

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kapp.data.product.model.Product
import com.lucasferreiramachado.kapp.data.purchase.model.ShoppingCartProduct
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinator
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinatorAction
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.getValue

sealed class ProductsCoordinatorAction: KCoordinatorAction {
    data object StartProductList : ProductsCoordinatorAction()
    data class StartProductDetail(val product: Product) : ProductsCoordinatorAction()
    data class StartPurchaseProduct(val product: ShoppingCartProduct) : ProductsCoordinatorAction()
}

class ProductsCoordinator(
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<ProductsCoordinatorAction>, KoinComponent {
    private val purchaseProductCoordinator: PurchaseProductCoordinator by inject { parametersOf(this) }
    private val productListCoordinator: ProductListCoordinator by inject { parametersOf(this) }

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