package com.most.products.application.di.feature

import com.most.products.application.core.dispatcher.DispatcherProviders
import com.most.products.application.core.dispatcher.DispatcherProvidersImpl
import com.most.products.application.di.domain.departmentsDomainModule
import com.most.products.application.ui.home.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val departmentsFeatureModule = module {
    includes(departmentsDomainModule)
    single<DispatcherProviders> { DispatcherProvidersImpl() }
    viewModel { HomeViewModel(get(), get()) }
}
