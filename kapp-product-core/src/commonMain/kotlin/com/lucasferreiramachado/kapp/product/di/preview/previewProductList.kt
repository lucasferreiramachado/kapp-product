package com.lucasferreiramachado.kapp.product.di.preview

import com.lucasferreiramachado.kapp.data.product.FakeProductRepository
import com.lucasferreiramachado.kapp.data.product.ProductRepository
import com.lucasferreiramachado.kapp.product.ProductsCoordinator
import com.lucasferreiramachado.kapp.product.di.modules.productList
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val previewProductList: Module = module {
    singleOf<ProductRepository>(::FakeProductRepository)
    includes(productList)
    single<ProductListCoordinator> { ProductListCoordinator(get<ProductsCoordinator>()) }
    single<ProductsCoordinator> { ProductsCoordinator(ProductListRootCoordinator()) }
}

private class ProductListRootCoordinator() : KCoordinator<KCoordinatorAction> {
    override val parent: KCoordinator<*>? = null
    override fun handle(action: KCoordinatorAction) {}
}