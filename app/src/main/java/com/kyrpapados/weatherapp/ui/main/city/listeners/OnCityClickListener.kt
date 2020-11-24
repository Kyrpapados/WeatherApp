package com.kyrpapados.weatherapp.ui.main.city.listeners

import com.kyrpapados.weatherapp.db.entities.CityData

interface OnCityClickListener {

    fun onCityForecastClickListener(city: CityData)

    fun onCityHourlyForecastClickListener(city: CityData)

    fun onCityDeleteItemClickListener(city: CityData)

}