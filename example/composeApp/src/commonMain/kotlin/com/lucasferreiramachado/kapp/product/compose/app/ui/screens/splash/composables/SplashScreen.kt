package com.lucasferreiramachado.kapp.product.compose.app.ui.screens.splash.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.splash.SplashViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SplashView(state) {  event -> viewModel.onEvent(event) }
}