package com.lucasferreiramachado.kapp.product.list.ui.screens.list

import com.lucasferreiramachado.kapp.data.product.model.Product

sealed class ListUiEvent {
    object BackButtonPressed : ListUiEvent()
    data class ProductItemSelected(val item: Product) : ListUiEvent()
}