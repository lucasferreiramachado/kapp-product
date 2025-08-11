package com.lucasferreiramachado.kapp.product.list.ui.screens.list.composables

import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.data.product.FakeProductRepository
import com.lucasferreiramachado.kapp.data.product.ProductRepository
import com.lucasferreiramachado.kapp.product.list.domain.usecases.GetProductsUseCase
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinatorAction
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListUiEvent
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListUiState
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator

@Composable
fun previewListScreen(
    state: ListUiState,
    events: List<ListUiEvent>
) {
    val viewModel = PreviewListViewModel(state)
    events.forEach {  event -> viewModel.onEvent(event) }
    ListScreen(
        viewModel
    )
}

private class PreviewListViewModel(
    initialState: ListUiState,
    coordinator: KCoordinator<ProductListCoordinatorAction>? = null,
    getProductsUseCase: GetProductsUseCase = GetProductsUseCase(
        PreviewProductRepositoryFactory().create()
    )
) : ListViewModel(
    initialState,
    coordinator,
    getProductsUseCase,
)

private class PreviewProductRepositoryFactory {
    private val repository: ProductRepository = FakeProductRepository()
    fun create(): ProductRepository {
        return repository
    }
}