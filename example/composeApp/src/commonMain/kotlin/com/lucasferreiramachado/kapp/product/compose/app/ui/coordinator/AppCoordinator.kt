package com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lucasferreiramachado.kapp.product.compose.app.ui.navigation.deeplinkNavigation
import com.lucasferreiramachado.kapp.product.compose.app.ui.navigation.splashNavigation
import com.lucasferreiramachado.kapp.product.compose.example.ui.coordinator.ExampleCoordinator
import com.lucasferreiramachado.kapp.product.compose.example.ui.coordinator.ExampleCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.compose.RootComposeKCoordinator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.getValue

class AppCoordinator(
    override val parent: KCoordinator<*>? = null
) : RootComposeKCoordinator<AppCoordinatorAction>, KoinComponent {
    private var navHostController: NavHostController? = null
    private var initialAction: AppCoordinatorAction? = null
    private val exampleCoordinator: ExampleCoordinator by inject { parametersOf(this) }

    override fun handle(action: AppCoordinatorAction) {
        when (action) {
            is AppCoordinatorAction.StartExample -> {
                exampleCoordinator.trigger(ExampleCoordinatorAction.StartExample)
            }
            is AppCoordinatorAction.GoToBackScreen -> {
                navHostController?.popBackStack()
            }
            is AppCoordinatorAction.AppInitialized -> {
                navHostController?.popBackStack()
                initialAction?.let { trigger(it) }
            }
        }
    }

    override fun setupNavigation(
        initialAction: AppCoordinatorAction,
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
    ) {
        this.initialAction = initialAction
        this.navHostController = navHostController

        exampleCoordinator.setupNavigation(navGraphBuilder, navHostController)

        navGraphBuilder.deeplinkNavigation(this)
        navGraphBuilder.splashNavigation(this)
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

