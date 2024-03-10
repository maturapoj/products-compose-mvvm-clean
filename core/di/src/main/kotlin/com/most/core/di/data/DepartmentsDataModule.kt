package com.most.core.di.data


import com.most.core.data.api.ApiService
import com.most.core.data.repository.DepartmentsRepository
import com.most.core.data.repository.DepartmentsRepositoryImpl
import com.most.core.di.network.retrofitModule
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val departmentsDataModule = module {
    includes(retrofitModule)
    factory { provideDepartmentsService(get(named("RETROFIT"))) }
    single<DepartmentsRepository> { DepartmentsRepositoryImpl(get()) }
}

private fun provideDepartmentsService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}
