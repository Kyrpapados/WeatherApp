package com.kyrpapados.weatherapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyrpapados.weatherapp.util.misc.ErrorData
import com.kyrpapados.weatherapp.util.misc.Event
import com.kyrpapados.weatherapp.util.misc.LoadingLiveData
import com.kyrpapados.weatherapp.util.misc.NoInternetData
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    fun launch(function: suspend () -> Unit) {
        LoadingLiveData.value = Event(true)

        viewModelScope.launch {
            function.invoke()
        }.apply {
            invokeOnCompletion {
                LoadingLiveData.value = Event(false)
            }
        }
    }

    fun showNoInternetDialog() {
        NoInternetData.value = Event(true)
    }

    fun showGeneralError() {
        ErrorData.value = Event(true)
    }

}