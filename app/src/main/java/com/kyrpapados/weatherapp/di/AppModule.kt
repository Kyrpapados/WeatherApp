package com.kyrpapados.weatherapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.kyrpapados.weatherapp.db.AppDatabase
import com.kyrpapados.weatherapp.repository.DatabaseRepository
import com.kyrpapados.weatherapp.repository.DatabaseRepositoryImpl
import com.kyrpapados.weatherapp.repository.WeatherRepository
import com.kyrpapados.weatherapp.repository.WeatherRepositoryImpl
import com.kyrpapados.weatherapp.util.helper.PreferencesHelper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

val appModule = module {

    single { provideMoshi() }
    single { provideSharedPrefs(get()) }
    single { provideDatabase(get()) }
    single { WeatherRepositoryImpl(get(), get()) as WeatherRepository }
    single { DatabaseRepositoryImpl(get()) as DatabaseRepository }

}

fun provideMoshi(): Moshi {
    return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}

fun provideSharedPrefs(context: Context): PreferencesHelper {
    val sharedPrefs = context.getSharedPreferences("weather_app_prefs", Context.MODE_PRIVATE)
    return PreferencesHelper(sharedPrefs)
}

fun provideDatabase(application: Application): AppDatabase =
    Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java, "weatherapp.db"
    )
        .build()
