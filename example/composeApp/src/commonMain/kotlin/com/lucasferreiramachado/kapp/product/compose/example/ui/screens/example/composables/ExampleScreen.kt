package com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example.ExampleViewModel

@Composable
fun ExampleScreen(
    viewModel: ExampleViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ExampleView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}