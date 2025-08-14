package com.lucasferreiramachado.kapp.product.di

import com.lucasferreiramachado.kapp.data.product.FakeProductRepository
import com.lucasferreiramachado.kapp.data.product.ProductRepository
import com.lucasferreiramachado.kapp.data.purchase.FakePurchaseRepository
import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kapp.product.ProductsCoordinator
import com.lucasferreiramachado.kapp.product.list.domain.usecases.GetProductsUseCase
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinator
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailViewModel
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListViewModel
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.GetCurrentPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchaseAddressUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchasePaymentMethodUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.StarNewPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.AddressViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.PaymentViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val productModule: Module = module {
    single { FakeProductRepository() }
    singleOf<ProductRepository>(::FakeProductRepository)
    single<PurchaseRepository> { FakePurchaseRepository(null) }

    singleOf(::GetProductsUseCase)
    singleOf(::GetCurrentPurchaseUseCase)
    singleOf(::SetPurchaseAddressUseCase)
    singleOf(::SetPurchasePaymentMethodUseCase)
    singleOf(::StarNewPurchaseUseCase)

    single<PurchaseProductCoordinator> { (parent: KCoordinator<*>) -> PurchaseProductCoordinator(parent, get()) }
    single<ProductListCoordinator> { (parent: KCoordinator<*>) -> ProductListCoordinator(parent) }
    single<ProductsCoordinator> { (parent: KCoordinator<*>) -> ProductsCoordinator(parent) }

    viewModelOf(::ListViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::AddressViewModel)
    viewModelOf(::PaymentViewModel)
    viewModelOf(::CheckoutViewModel)
}