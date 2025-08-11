package com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink

data class DeeplinkUiState(
    val deeplinkUri: String,
    val showBackButton: Boolean = false,
)