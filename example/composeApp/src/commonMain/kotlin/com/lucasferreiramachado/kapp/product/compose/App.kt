package com.lucasferreiramachado.kapp.product.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator.AppCoordinator
import com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator.AppCoordinatorAction
import com.lucasferreiramachado.kapp.product.compose.app.ui.navigation.AppNavigationRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val appCoordinator = AppCoordinator()
        appCoordinator.start(
            AppNavigationRoute.SplashScreen,
            initialAction = AppCoordinatorAction.StartExample
        )
    }
}