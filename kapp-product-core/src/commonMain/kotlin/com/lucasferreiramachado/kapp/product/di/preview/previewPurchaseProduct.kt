package com.lucasferreiramachado.kapp.product.di.preview

import com.lucasferreiramachado.kapp.data.purchase.FakePurchaseRepository
import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kapp.product.ProductsCoordinator
import com.lucasferreiramachado.kapp.product.di.modules.purchaseProduct
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import org.koin.core.module.Module
import org.koin.dsl.module

val previewPurchaseProduct: Module = module {
    single<PurchaseRepository> { FakePurchaseRepository(null) }
    includes(purchaseProduct)
    single<PurchaseProductCoordinator> {
        PurchaseProductCoordinator(
            get<ProductsCoordinator>(),
            get()
        )
    }
    single<ProductsCoordinator> { ProductsCoordinator(PurchaseProductRootCoordinator()) }
}

private class PurchaseProductRootCoordinator() : KCoordinator<KCoordinatorAction> {
    override val parent: KCoordinator<*>? = null
    override fun handle(action: KCoordinatorAction) {}
}