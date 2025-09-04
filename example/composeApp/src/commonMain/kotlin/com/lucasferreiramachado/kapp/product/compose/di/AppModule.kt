package com.lucasferreiramachado.kapp.product.compose.di

import com.lucasferreiramachado.kapp.auth.login.di.authModule
import com.lucasferreiramachado.kapp.product.compose.app.ui.coordinator.AppCoordinator
import com.lucasferreiramachado.kapp.product.compose.di.modules.dataModule
import com.lucasferreiramachado.kapp.product.compose.example.ui.coordinator.ExampleCoordinator
import com.lucasferreiramachado.kapp.product.di.productsModule
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    includes(dataModule)
    includes(authModule)
    includes(productsModule)

    single<ExampleCoordinator> { (parent: AppCoordinator) -> ExampleCoordinator(parent) }
    single<AppCoordinator> { AppCoordinator() }
}