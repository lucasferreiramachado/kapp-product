package com.lucasferreiramachado.kapp.product.compose.example.ui.navigation

import kotlinx.serialization.Serializable

sealed class ExampleNavigationRoute {
    @Serializable data object ExampleScreen: ExampleNavigationRoute()
}