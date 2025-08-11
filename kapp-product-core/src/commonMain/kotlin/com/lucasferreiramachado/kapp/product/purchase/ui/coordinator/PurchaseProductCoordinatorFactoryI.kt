package com.lucasferreiramachado.kapp.product.purchase.ui.coordinator

import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.GetCurrentPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchaseAddressUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchasePaymentMethodUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.StarNewPurchaseUseCase
import com.lucasferreiramachado.kcoordinator.KCoordinator

interface PurchaseProductCoordinatorFactoryI {

    val setPurchasePaymentMethodUseCase: SetPurchasePaymentMethodUseCase
    val setPurchaseAddressUseCase: SetPurchaseAddressUseCase
    val getCurrentPurchaseUseCase: GetCurrentPurchaseUseCase
    val startNewPurchaseUseCase: StarNewPurchaseUseCase

    fun create(parent: KCoordinator<*>): PurchaseProductCoordinator
}