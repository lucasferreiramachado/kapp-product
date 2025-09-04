package com.lucasferreiramachado.kapp.product.list.ui.screens.detail.composables

import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.product.di.preview.previewProductList
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailUiEvent
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailUiState
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailViewModel
import org.koin.compose.KoinApplicationPreview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun previewDetailScreen(
    state: DetailUiState,
    events: List<DetailUiEvent>
) {
    KoinApplicationPreview(
        application = { modules(previewProductList) }
    ) {
        val viewModel = koinViewModel<DetailViewModel>(
            parameters = { parametersOf(
                state
            ) }
        )
        events.forEach { event -> viewModel.onEvent(event) }
        DetailScreen(viewModel)
    }
}