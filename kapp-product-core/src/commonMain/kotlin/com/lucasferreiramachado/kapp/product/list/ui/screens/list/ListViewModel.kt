package com.lucasferreiramachado.kapp.product.list.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasferreiramachado.kapp.product.list.domain.usecases.GetProductsUseCase
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinator
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinatorAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class ListViewModel(
    initialState: ListUiState = ListUiState(),
    var coordinator: ProductListCoordinator,
    val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<ListUiState> = _state.asStateFlow()
    
    fun onEvent(event: ListUiEvent) {
        when (event) {
            is ListUiEvent.BackButtonPressed -> {
                coordinator.trigger(ProductListCoordinatorAction.GoBack)
            }
            is ListUiEvent.ProductItemSelected -> {
                coordinator.trigger(ProductListCoordinatorAction.ShowDetail(event.item))
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val products = getProductsUseCase.execute()
            viewModelScope.launch(Dispatchers.Main) {
                _state.update { state ->
                    state.copy(
                        products = products
                    )
                }
            }
        }
    }
}