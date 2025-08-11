package com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.composables

import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.DeeplinkUiEvent
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.DeeplinkUiState
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.DeeplinkViewModel

@Composable
fun previewDeeplinkScreen(
    state: DeeplinkUiState,
    events: List<DeeplinkUiEvent>
) {
    val viewModel = DeeplinkViewModel(state)
    events.forEach {  event -> viewModel.onEvent(event) }
    DeeplinkScreen(
        viewModel
    )
}