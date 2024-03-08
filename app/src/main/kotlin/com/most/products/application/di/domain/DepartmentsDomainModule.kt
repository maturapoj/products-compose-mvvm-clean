package com.most.products.application.di.domain

import com.most.products.application.di.data.departmentsDataModule
import com.most.products.application.domain.usecase.DepartmentsUseCase
import org.koin.dsl.module

val departmentsDomainModule = module {
    includes(departmentsDataModule)
    single { DepartmentsUseCase(get()) }
}
