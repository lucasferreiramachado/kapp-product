package com.lucasferreiramachado.kapp.product.list.ui.screens.list

import com.lucasferreiramachado.kapp.data.product.model.Product

data class ListUiState(
    var isLoading: Boolean = false,
    var products: List<Product> = emptyList(),
)