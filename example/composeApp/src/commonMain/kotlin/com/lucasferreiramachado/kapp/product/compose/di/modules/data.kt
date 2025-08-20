package com.lucasferreiramachado.kapp.product.compose.di.modules

import com.lucasferreiramachado.kapp.data.product.FakeProductRepository
import com.lucasferreiramachado.kapp.data.product.ProductRepository
import com.lucasferreiramachado.kapp.data.purchase.FakePurchaseRepository
import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kapp.data.user.FakeUserRepository
import com.lucasferreiramachado.kapp.data.user.UserRepository
import com.lucasferreiramachado.kapp.product.di.modules.purchaseProduct
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule: Module = module {
    single<UserRepository> { FakeUserRepository() }
    single<PurchaseRepository> { FakePurchaseRepository(null) }
    singleOf<ProductRepository>(::FakeProductRepository)
}