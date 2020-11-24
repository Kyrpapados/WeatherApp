package com.kyrpapados.weatherapp

import android.app.Application
import android.os.StrictMode
import androidx.appcompat.app.AppCompatDelegate
import com.kyrpapados.weatherapp.di.appModule
import com.kyrpapados.weatherapp.di.networkModule
import com.kyrpapados.weatherapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class WeatherAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            // declare used Android context
            androidContext(this@WeatherAppApplication)

            // declare modules
            modules(listOf(appModule, networkModule, viewModelModule))
        }

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        Timber.plant(Timber.DebugTree())
    }

}