package com.lucasferreiramachado.kapp.product.di

import com.lucasferreiramachado.kapp.data.product.FakeProductRepository
import com.lucasferreiramachado.kapp.data.product.ProductRepository
import com.lucasferreiramachado.kapp.data.purchase.FakePurchaseRepository
import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kapp.product.ProductsCoordinator
import com.lucasferreiramachado.kapp.product.list.ProductListCoordinator
import com.lucasferreiramachado.kapp.product.purchase.PurchaseProductCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinator

object PurchaseRepositoryFactory {
    private val repository: PurchaseRepository = FakePurchaseRepository()

    fun create(): PurchaseRepository {
        return repository
    }
}

class ProductRepositoryFactory {

    fun create(): ProductRepository = FakeProductRepository()
}

class ProductsCoordinatorFactory {
    fun create(parent: KCoordinator<*>): ProductsCoordinator = ProductsCoordinator(parent = parent)
}

class PurchaseProductCoordinatorFactory {
    fun create(parent: KCoordinator<*>): PurchaseProductCoordinator = PurchaseProductCoordinator(parent)
}

class ProductListCoordinatorFactory {
    fun create(parent: KCoordinator<*>): ProductListCoordinator = ProductListCoordinator(parent)
}