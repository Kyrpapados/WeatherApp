package com.kyrpapados.weatherapp.di

import com.kyrpapados.weatherapp.base.BaseViewModel
import com.kyrpapados.weatherapp.ui.main.city.CityViewModel
import com.kyrpapados.weatherapp.ui.main.details.DetailsViewModel
import com.kyrpapados.weatherapp.ui.main.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { BaseViewModel() }
    viewModel { CityViewModel(get(), get()) }
    viewModel { ForecastViewModel(get()) }
    viewModel { DetailsViewModel(get()) }

}