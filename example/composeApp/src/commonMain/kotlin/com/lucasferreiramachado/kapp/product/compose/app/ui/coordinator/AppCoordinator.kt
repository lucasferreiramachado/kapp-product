package com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lucasferreiramachado.kapp.product.compose.app.ui.navigation.deeplinkNavigation
import com.lucasferreiramachado.kapp.product.compose.app.ui.navigation.splashNavigation
import com.lucasferreiramachado.kapp.product.compose.di.ExampleCoordinatorFactory
import com.lucasferreiramachado.kapp.product.compose.example.ui.coordinator.ExampleCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.compose.RootComposeKCoordinator
import kotlinx.coroutines.delay

class AppCoordinator(
    exampleCoordinatorFactory: ExampleCoordinatorFactory = ExampleCoordinatorFactory(),
    override val parent: KCoordinator<*>? = null
) : RootComposeKCoordinator<AppCoordinatorAction> {
    private var navHostController: NavHostController? = null
    private var exampleCoordinator = exampleCoordinatorFactory.create(parent = this)

    override fun handle(action: AppCoordinatorAction) {
        when (action) {
            is AppCoordinatorAction.StartExample -> {
                exampleCoordinator.trigger(ExampleCoordinatorAction.StartExample)
            }
            is AppCoordinatorAction.GoToBackScreen -> {
                navHostController?.popBackStack()
            }
        }
    }

    override fun setupNavigation(
        initialAction: AppCoordinatorAction,
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
    ) {
        this.navHostController = navHostController

        exampleCoordinator.setupNavigation(navGraphBuilder, navHostController)

        navGraphBuilder.deeplinkNavigation(this)
        navGraphBuilder.splashNavigation(
            onSplashScreenLaunched = {
                delay(1500)
                navHostController.popBackStack()
                trigger(initialAction)
            }
        )
    }

    @Composable
    override fun start(startDestination: Any, initialAction: AppCoordinatorAction) {
        val navHostController = rememberNavController()

        NavHost(
            startDestination = startDestination,
            navController = navHostController
        ) {
            setupNavigation(
                initialAction,
                this,
                navHostController
            )
        }
    }

    fun canGoBack() : Boolean = navHostController?.previousBackStackEntry != null
}

