package com.kyrpapados.weatherapp.ui.main.city

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kyrpapados.weatherapp.base.BaseViewModel
import com.kyrpapados.weatherapp.db.entities.CityData
import com.kyrpapados.weatherapp.model.local.forecast.Weather
import com.kyrpapados.weatherapp.model.local.search.SearchItem
import com.kyrpapados.weatherapp.repository.DatabaseRepository
import com.kyrpapados.weatherapp.repository.WeatherRepository
import com.kyrpapados.weatherapp.repository.data.StateWrapper
import com.kyrpapados.weatherapp.util.helper.DateHelper
import com.kyrpapados.weatherapp.util.misc.Event

class CityViewModel(private val weatherRepository: WeatherRepository, private val databaseRepository: DatabaseRepository): BaseViewModel() {

    private val _loadingUI = MutableLiveData<Event<Int>>()
    val loadingUI: LiveData<Event<Int>>
        get() = _loadingUI

    private val _retrieveCities = MutableLiveData<Event<List<CityData>>>()
    val retrieveCities: LiveData<Event<List<CityData>>>
        get() = _retrieveCities

    private val _searchCity = MutableLiveData<Event<List<SearchItem>>>()
    val searchCity: LiveData<Event<List<SearchItem>>>
        get() = _searchCity

    private val _addCity = MutableLiveData<Event<Boolean>>()
    val addCity: LiveData<Event<Boolean>>
        get() = _addCity

    private val _todayForecastList = MutableLiveData<Event<List<Weather>>>()
    val todayForecastList: LiveData<Event<List<Weather>>>
        get() = _todayForecastList

    private lateinit var cityList: List<CityData>

    fun getCities() {
        launch {
            when(val result = databaseRepository.getCities()){
                is StateWrapper.Success -> {
                    cityList = result.data
                    _retrieveCities.value = Event(result.data)
                }
            }
        }
    }

    fun searchCity(city: String) {
        launch {
            val result = weatherRepository.searchCity(city)

            when(result){
                is StateWrapper.Success -> {
                    _searchCity.value = Event(result.data.search_api.result)
                }

                is StateWrapper.Error -> {

                }
            }

        }
    }

    fun saveCity(cities: List<SearchItem>) {
        launch {
            for (city in cities){
                val cityItem = CityData()
                cityItem.areaName = city.areaName[0].value
                cityItem.country = city.country[0].value
                cityItem.region = city.region[0].value
                cityItem.latitude = city.latitude
                cityItem.longitude = city.longitude

                if (!cityList.contains(cityItem)){
                    databaseRepository.saveCity(cityItem)
                }
            }

            _addCity.value = Event(true)
        }
    }

    fun deleteCity(city: CityData){
        launch {
            databaseRepository.deleteCity(city.areaName)

            when(val result = databaseRepository.getCities()){
                is StateWrapper.Success -> {
                    cityList = result.data
                    _retrieveCities.value = Event(result.data)
                }
            }
        }
    }

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