package com.kyrpapados.weatherapp.repository

import com.kyrpapados.weatherapp.model.response.ForecastResponse
import com.kyrpapados.weatherapp.model.response.SearchCityResponse
import com.kyrpapados.weatherapp.repository.data.StateWrapper
import com.kyrpapados.weatherapp.repository.data.performCall
import com.kyrpapados.weatherapp.util.Statics.Companion.FORMAT
import com.kyrpapados.weatherapp.util.Statics.Companion.KEY
import com.kyrpapados.weatherapp.util.Statics.Companion.MCA
import com.kyrpapados.weatherapp.util.Statics.Companion.NUM_OF_DAYS
import com.kyrpapados.weatherapp.util.Statics.Companion.POPULAR
import com.kyrpapados.weatherapp.util.Statics.Companion.TP
import com.kyrpapados.weatherapp.util.extensions.toResult
import com.kyrpapados.weatherapp.util.helper.ConnectivityHelper

interface WeatherRepository {

    suspend fun searchCity(city: String): StateWrapper<SearchCityResponse>

    suspend fun getForecast(city: String): StateWrapper<ForecastResponse>

    suspend fun getTodayForecast(city: String, date: String): StateWrapper<ForecastResponse>

}

class WeatherRepositoryImpl(private val apiDataSource: ApiDataSource, private val connectivityHelper: ConnectivityHelper) : WeatherRepository {

    override suspend fun searchCity(city: String): StateWrapper<SearchCityResponse> =
        performCall(connectivityHelper) {
            apiDataSource.searchCity(key = KEY, q = city, format = FORMAT, popular = POPULAR).toResult()
        }

    override suspend fun getForecast(city: String): StateWrapper<ForecastResponse> =
        performCall(connectivityHelper) {
            apiDataSource.getForecast(key = KEY, q = city, format = FORMAT, num_of_days = NUM_OF_DAYS, mca = MCA, tp = TP).toResult()
        }

    override suspend fun getTodayForecast(city: String, date: String): StateWrapper<ForecastResponse> =
        performCall(connectivityHelper) {
            apiDataSource.getTodayForecast(key = KEY, q = city, format = FORMAT, date = date, mca = MCA, tp = TP).toResult()
        }

}