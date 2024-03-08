package com.most.products.application.di

import com.most.products.application.di.feature.departmentsFeatureModule
import org.koin.dsl.module

val AppModule = module {
    includes(departmentsFeatureModule)
}
