package com.most.core.di.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val NetworkModule = module {
    single { provideOkHttpClient(get()) }
    single { debugCollector(androidContext()) }
    single { debugInterceptor(androidContext()) }
}

private fun provideOkHttpClient(
    chuckerInterceptor: ChuckerInterceptor,
): OkHttpClient {

    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor {
            val original = it.request()
            val newRequestBuilder = original.newBuilder()
            newRequestBuilder.addHeader("Content-Type", "application/json")
            it.proceed(newRequestBuilder.build())
        }
        .addInterceptor {
            val original = it.request()
            val newUrl = original.url
                .newBuilder()
                .build()
            val newRequest = original
                .newBuilder()
                .url(newUrl)
                .build()
            it.proceed(newRequest)
        }
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        client.addInterceptor(chuckerInterceptor)
    }

    return client.build()
}

private fun debugInterceptor(context: Context): ChuckerInterceptor =
    ChuckerInterceptor.Builder(context)
        .collector(debugCollector(context))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(true)
        .build()

private fun debugCollector(context: Context) =
    ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR,
    )
