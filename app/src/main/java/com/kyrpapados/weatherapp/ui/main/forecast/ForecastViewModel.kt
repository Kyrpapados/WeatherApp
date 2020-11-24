package com.kyrpapados.weatherapp.ui.main.forecast

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kyrpapados.weatherapp.base.BaseViewModel
import com.kyrpapados.weatherapp.model.local.forecast.Weather
import com.kyrpapados.weatherapp.repository.WeatherRepository
import com.kyrpapados.weatherapp.repository.data.StateWrapper
import com.kyrpapados.weatherapp.util.misc.Event

class ForecastViewModel(private val weatherRepository: WeatherRepository): BaseViewModel() {

    private val _loadingUI = MutableLiveData<Event<Int>>()
    val loadingUI: LiveData<Event<Int>>
        get() = _loadingUI

    private val _forecastList = MutableLiveData<Event<List<Weather>>>()
    val forecastList: LiveData<Event<List<Weather>>>
        get() = _forecastList

    fun getWeatherForecast(cityName: String) {
        launch {
            _loadingUI.value = Event(View.VISIBLE)

            val result = weatherRepository.getForecast(city = cityName)

            when(result){
                is StateWrapper.Success -> {
                    _loadingUI.value = Event(View.GONE)
                    _forecastList.value = Event(result.data.data.weather)
                }

                is StateWrapper.Error -> {
                    _loadingUI.value = Event(View.GONE)
                    showGeneralError()
                }

                is StateWrapper.NoInternet -> {
                    _loadingUI.value = Event(View.GONE)
                    showNoInternetDialog()
                }
            }
        }
    }
}