package com.lucasferreiramachado.kapp.product.di.modules

import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.GetCurrentPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchaseAddressUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchasePaymentMethodUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.StarNewPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.AddressViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.PaymentViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val purchaseProduct: Module = module {
    singleOf(::GetCurrentPurchaseUseCase)
    singleOf(::SetPurchaseAddressUseCase)
    singleOf(::SetPurchasePaymentMethodUseCase)
    singleOf(::StarNewPurchaseUseCase)

    viewModelOf(::AddressViewModel)
    viewModelOf(::PaymentViewModel)
    viewModelOf(::CheckoutViewModel)
}