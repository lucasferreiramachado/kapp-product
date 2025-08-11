package com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator.AppCoordinator
import com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator.AppCoordinatorAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeeplinkViewModel(
    initialState: DeeplinkUiState,
    val coordinator: AppCoordinator? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<DeeplinkUiState> = _state.asStateFlow()

    fun onEvent(event: DeeplinkUiEvent) {
        when (event) {
            is DeeplinkUiEvent.OnBackPressed -> {
                coordinator?.trigger(AppCoordinatorAction.GoToBackScreen)
            }
        }
    }

    init {
        viewModelScope.launch {
            checkBackButtonVisibility()
        }
    }

    fun checkBackButtonVisibility() {
        _state.update {
            it.copy(
                showBackButton = coordinator?.canGoBack() ?: false
            )
        }
    }
}