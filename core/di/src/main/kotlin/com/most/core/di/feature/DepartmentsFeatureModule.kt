package com.most.core.di.feature


import com.most.core.common.dispatcher.DispatcherProviders
import com.most.core.common.dispatcher.DispatcherProvidersImpl
import com.most.core.di.domain.departmentsDomainModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val departmentsFeatureModule = module {
    includes(departmentsDomainModule)
    single<DispatcherProviders> { DispatcherProvidersImpl() }
    viewModel { HomeViewModel(get(), get()) }
}
