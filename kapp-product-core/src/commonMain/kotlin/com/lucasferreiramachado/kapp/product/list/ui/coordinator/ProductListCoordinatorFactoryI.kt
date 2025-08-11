package com.lucasferreiramachado.kapp.product.list.ui.coordinator

import com.lucasferreiramachado.kapp.product.list.domain.usecases.GetProductsUseCase
import com.lucasferreiramachado.kcoordinator.KCoordinator

interface ProductListCoordinatorFactoryI {

    val getProductsUseCase: GetProductsUseCase

    fun create(parent: KCoordinator<*>): ProductListCoordinator
}