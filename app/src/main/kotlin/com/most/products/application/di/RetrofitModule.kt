package com.most.products.application.di

import com.most.products.application.BuildConfig
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    includes(NetworkModule)
    factory(named("RETROFIT")) {  provideRetrofit(get()) }
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
): Retrofit {
    return Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}
