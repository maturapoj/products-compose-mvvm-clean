package com.most.core.di

import com.most.core.di.feature.departmentsFeatureModule
import org.koin.dsl.module

val AppModule = module {
    includes(departmentsFeatureModule)
}
