package com.most.core.di.domain

import com.most.core.di.data.departmentsDataModule
import org.koin.dsl.module

val departmentsDomainModule = module {
    includes(departmentsDataModule)
    single { DepartmentUseCase(get()) }
}
