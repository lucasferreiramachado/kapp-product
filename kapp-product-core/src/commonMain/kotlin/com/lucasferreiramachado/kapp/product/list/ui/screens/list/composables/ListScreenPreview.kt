package com.lucasferreiramachado.kapp.product.list.ui.screens.list.composables

import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.product.di.previewModule
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListUiEvent
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListUiState
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListViewModel
import org.koin.compose.KoinApplicationPreview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun previewListScreen(
    state: ListUiState,
    events: List<ListUiEvent>
) {
    KoinApplicationPreview(
        application = { modules(previewModule) }
    ) {
        val viewModel = koinViewModel<ListViewModel> {
            parametersOf(state)
        }
        events.forEach { event -> viewModel.onEvent(event) }
        ListScreen(viewModel)
    }
}