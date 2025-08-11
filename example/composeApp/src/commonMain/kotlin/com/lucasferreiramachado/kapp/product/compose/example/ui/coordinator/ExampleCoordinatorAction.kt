package com.lucasferreiramachado.kapp.product.compose.example.ui.coordinator

import com.lucasferreiramachado.kcoordinator.KCoordinatorAction

sealed class ExampleCoordinatorAction: KCoordinatorAction {
    data object StartExample : ExampleCoordinatorAction()
    data object StartProductListFlow : ExampleCoordinatorAction()
}