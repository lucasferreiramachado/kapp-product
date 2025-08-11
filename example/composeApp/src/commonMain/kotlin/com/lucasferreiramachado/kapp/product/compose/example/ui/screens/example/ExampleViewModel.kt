package com.lucasferreiramachado.kapp.product.compose.example.ui.screens.example

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kapp.product.compose.example.ui.coordinator.ExampleCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExampleViewModel(
    initialState: ExampleUiState,
    val coordinator: KCoordinator<ExampleCoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<ExampleUiState> = _state.asStateFlow()

    fun onEvent(event: ExampleUiEvent) {
        when (event) {
            is ExampleUiEvent.ItemSelected -> {
                coordinator?.trigger(event.item.action)
            }
        }
    }
}

