package com.lucasferreiramachado.kapp.product.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator.AppCoordinator
import com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator.AppCoordinatorAction
import com.lucasferreiramachado.kapp.product.compose.app.ui.navigation.AppNavigationRoute
import com.lucasferreiramachado.kapp.product.compose.di.KoinApp
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {
    KoinMultiplatformApplication(KoinApp) {
        MaterialTheme {
            val appCoordinator = AppCoordinator()
            appCoordinator.start(
                AppNavigationRoute.SplashScreen,
                initialAction = AppCoordinatorAction.StartExample
            )
        }
    }
}