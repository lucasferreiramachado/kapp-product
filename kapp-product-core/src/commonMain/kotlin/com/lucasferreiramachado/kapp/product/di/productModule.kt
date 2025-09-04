package com.lucasferreiramachado.kapp.product.di

import com.lucasferreiramachado.kapp.product.ProductsCoordinator
import com.lucasferreiramachado.kapp.product.di.modules.productList
import com.lucasferreiramachado.kapp.product.di.modules.purchaseProduct
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinator
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinator
import org.koin.core.module.Module
import org.koin.dsl.module

val productsModule: Module = module {
    includes(productList)
    includes(purchaseProduct)
    single<PurchaseProductCoordinator> { (parent: KCoordinator<*>) -> PurchaseProductCoordinator(parent, get()) }
    single<ProductListCoordinator> { (parent: KCoordinator<*>) -> ProductListCoordinator(parent) }
    single<ProductsCoordinator> { (parent: KCoordinator<*>) -> ProductsCoordinator(parent) }
}