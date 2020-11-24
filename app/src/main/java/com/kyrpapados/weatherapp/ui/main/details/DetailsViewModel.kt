package com.kyrpapados.weatherapp.ui.main.details

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kyrpapados.weatherapp.base.BaseViewModel
import com.kyrpapados.weatherapp.model.local.forecast.Weather
import com.kyrpapados.weatherapp.repository.WeatherRepository
import com.kyrpapados.weatherapp.repository.data.StateWrapper
import com.kyrpapados.weatherapp.util.helper.DateHelper
import com.kyrpapados.weatherapp.util.misc.Event

class DetailsViewModel(private val weatherRepository: WeatherRepository): BaseViewModel() {

    private val _loadingUI = MutableLiveData<Event<Int>>()
    val loadingUI: LiveData<Event<Int>>
        get() = _loadingUI

    private val _todayForecastList = MutableLiveData<Event<List<Weather>>>()
    val todayForecastList: LiveData<Event<List<Weather>>>
        get() = _todayForecastList

    fun getTodayForecast(cityName: String) {
        launch {
            launch {
                _loadingUI.value = Event(View.VISIBLE)

                val result = weatherRepository.getTodayForecast(city = cityName, date = DateHelper().getTodayDate())

                when(result){
                    is StateWrapper.Success -> {
                        _loadingUI.value = Event(View.GONE)
                        _todayForecastList.value = Event(result.data.data.weather)
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
}