package com.lucasferreiramachado.kapp.product.purchase.ui.coordinator

import com.lucasferreiramachado.kapp.data.purchase.model.ShoppingCartProduct
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction

sealed class PurchaseProductCoordinatorAction: KCoordinatorAction {

    data class StartPurchase(val product: ShoppingCartProduct): PurchaseProductCoordinatorAction()

    data object ShowAddress: PurchaseProductCoordinatorAction()
    data object ShowPayment : PurchaseProductCoordinatorAction()
    data object ShowCheckout : PurchaseProductCoordinatorAction()

    data object PurchaseRealized : PurchaseProductCoordinatorAction()

    data object GoBack : PurchaseProductCoordinatorAction()
}