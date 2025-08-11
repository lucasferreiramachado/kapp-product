package com.lucasferreiramachado.kapp.product.compose.app.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.savedstate.read
import com.lucasferreiramachado.kapp.deeplink.AppDeeplink
import com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator.AppCoordinator
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.DeeplinkUiState
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.DeeplinkViewModel
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.composables.DeeplinkScreen


fun NavGraphBuilder.deeplinkNavigation(
    coordinator: AppCoordinator,
) {
    composable<AppNavigationRoute.DeeplinkScreen>(
        deepLinks = listOf(
            navDeepLink {
                uriPattern = AppDeeplink.defaultScheme().plus("{deeplinkPath}")
            }
        )
    ) { backStackEntry ->
        val deeplinkPath = backStackEntry.arguments?.read { getStringOrNull("deeplinkPath") }
        val deeplinkUri = AppDeeplink.defaultScheme().plus(deeplinkPath)
        val state = DeeplinkUiState(deeplinkUri)
        DeeplinkScreen(
            DeeplinkViewModel(state, coordinator = coordinator)
        )
    }
}