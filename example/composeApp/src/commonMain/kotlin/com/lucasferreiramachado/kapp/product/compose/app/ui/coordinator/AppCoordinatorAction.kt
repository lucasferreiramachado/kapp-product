package com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator

import com.lucasferreiramachado.kcoordinator.KCoordinatorAction

sealed class AppCoordinatorAction: KCoordinatorAction {
    data object StartExample : AppCoordinatorAction()
    data object GoToBackScreen : AppCoordinatorAction()

    data object AppInitialized : AppCoordinatorAction()
}