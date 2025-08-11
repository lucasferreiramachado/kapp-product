package com.lucasferreiramachado.kapp.product.list.ui.coordinator

import com.lucasferreiramachado.kapp.data.product.model.Product
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction

sealed class ProductListCoordinatorAction: KCoordinatorAction {
    data object ShowList: ProductListCoordinatorAction()
    data class ShowDetail(val product: Product) : ProductListCoordinatorAction()
    data object GoBack : ProductListCoordinatorAction()
    data object StarBuyProductFlow : ProductListCoordinatorAction()
}