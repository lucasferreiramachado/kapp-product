package com.lucasferreiramachado.kapp.product.list.ui.navigation

import kotlinx.serialization.Serializable

sealed class ProductListNavigationRoute {

    @Serializable object List: ProductListNavigationRoute()
    @Serializable data class Detail(val id: Int, val name: String, val price: Double): ProductListNavigationRoute()
}