package com.lucasferreiramachado.kapp.product.di.modules

import com.lucasferreiramachado.kapp.product.list.domain.usecases.GetProductsUseCase
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailViewModel
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val productList: Module = module {
    singleOf(::GetProductsUseCase)

    viewModelOf(::ListViewModel)
    viewModelOf(::DetailViewModel)
}