package com.lucasferreiramachado.kapp.product.compose.app.ui.screens.splash.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.splash.SplashUiState
import kotlinx.coroutines.CoroutineScope

@Composable
fun SplashScreen(
    state: SplashUiState = SplashUiState(),
    onSplashScreenLaunched: suspend CoroutineScope.() -> Unit
) {
    SplashView(state)
    LaunchedEffect(key1 = "splashScreenLaunched") {
        onSplashScreenLaunched()
    }
}