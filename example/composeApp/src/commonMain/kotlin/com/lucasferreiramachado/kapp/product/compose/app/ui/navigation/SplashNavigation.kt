package com.lucasferreiramachado.kapp.product.compose.app.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.splash.composables.SplashScreen
import kotlinx.coroutines.CoroutineScope

fun NavGraphBuilder.splashNavigation(
    onSplashScreenLaunched: suspend CoroutineScope.() -> Unit
) {
    composable<AppNavigationRoute.SplashScreen> {
        SplashScreen(
            onSplashScreenLaunched = onSplashScreenLaunched
        )
    }
}