package com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.DeeplinkViewModel

@Composable
fun DeeplinkScreen(
    viewModel: DeeplinkViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DeeplinkView(
        state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}