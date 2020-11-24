package com.kyrpapados.weatherapp.ui.main.forecast

import com.kyrpapados.weatherapp.db.entities.CityData
import com.kyrpapados.weatherapp.model.local.forecast.Weather

interface OnForecastClickListener {

    fun onForecastClickListener(weatherForecast: Weather)

}