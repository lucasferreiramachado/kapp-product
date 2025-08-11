package com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example

import com.lucasferreiramachado.kapp.product.compose.example.domain.model.ExampleItem

sealed class ExampleUiEvent {
    data class ItemSelected(val item: ExampleItem) : ExampleUiEvent()
}