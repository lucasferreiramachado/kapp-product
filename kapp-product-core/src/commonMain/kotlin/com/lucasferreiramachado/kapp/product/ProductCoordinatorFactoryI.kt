package com.lucasferreiramachado.kapp.product

import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinatorFactoryI
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinatorFactoryI
import com.lucasferreiramachado.kcoordinator.KCoordinator

interface ProductsCoordinatorFactoryI {

    val purchaseProductCoordinatorFactory: PurchaseProductCoordinatorFactoryI
    val productListCoordinatorFactory: ProductListCoordinatorFactoryI

    fun create(parent: KCoordinator<*>): ProductsCoordinator
}