package com.lucasferreiramachado.kapp.product.compose.example.ui.coordinator

import com.lucasferreiramachado.kapp.auth.login.ui.coordinator.AuthCoordinatorFactoryI
import com.lucasferreiramachado.kapp.product.ProductsCoordinatorFactoryI
import com.lucasferreiramachado.kcoordinator.KCoordinator

interface ExampleCoordinatorFactoryI {
    val productCoordinatorFactory: ProductsCoordinatorFactoryI
    val authCoordinatorFactory: AuthCoordinatorFactoryI

    fun create(parent: KCoordinator<*>): ExampleCoordinator
}