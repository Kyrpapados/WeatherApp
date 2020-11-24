package com.kyrpapados.weatherapp.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kyrpapados.weatherapp.BuildConfig
import com.kyrpapados.weatherapp.repository.ApiDataSource
import com.kyrpapados.weatherapp.util.helper.ConnectivityHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { provideConnectivityManager(get()) }

    single { ConnectivityHelper(get()) }

    single { provideOkHttpClient() }

    single { provideRetrofit<ApiDataSource>(get(), BuildConfig.API_BASE_URL) }
}

fun provideConnectivityManager(application: Application): ConnectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun provideOkHttpClient(): OkHttpClient {

    val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(httpLoggingInterceptor)
    }

    return okHttpClient.build()
}

inline fun <reified T> provideRetrofit(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    return retrofit.create(T::class.java)
}