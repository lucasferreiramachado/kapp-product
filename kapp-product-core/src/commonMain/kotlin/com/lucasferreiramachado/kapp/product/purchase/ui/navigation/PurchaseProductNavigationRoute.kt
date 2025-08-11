package com.lucasferreiramachado.kapp.product.purchase.ui.navigation

import kotlinx.serialization.Serializable

sealed class PurchaseProductNavigationRoute {
    @Serializable
    object Address: PurchaseProductNavigationRoute()
    @Serializable
    object Payment: PurchaseProductNavigationRoute()
    @Serializable
    object Checkout: PurchaseProductNavigationRoute()
}