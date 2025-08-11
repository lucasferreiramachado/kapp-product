package com.lucasferreiramachado.kapp.product.compose.app.ui.navigation

import kotlinx.serialization.Serializable

sealed class AppNavigationRoute {
    @Serializable data object SplashScreen: AppNavigationRoute()
    @Serializable data object DeeplinkScreen: AppNavigationRoute()
}