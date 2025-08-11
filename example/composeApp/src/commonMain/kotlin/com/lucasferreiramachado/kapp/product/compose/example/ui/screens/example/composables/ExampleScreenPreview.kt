package com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example.composables

import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example.ExampleUiEvent
import com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example.ExampleUiState
import com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example.ExampleViewModel

@Composable
fun previewExampleScreen(
    state: ExampleUiState,
    events: List<ExampleUiEvent>
) {
    val viewModel = ExampleViewModel(state)
    events.forEach {  event -> viewModel.onEvent(event) }
    ExampleScreen(
        viewModel
    )
}