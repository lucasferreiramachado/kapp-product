package com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink

sealed class DeeplinkUiEvent {
    data object OnBackPressed : DeeplinkUiEvent()
}