package com.lucasferreiramachado.kapp.product.compose.di

import org.koin.dsl.koinConfiguration

val KoinApp = koinConfiguration {
    modules(
        appModule
    )
}