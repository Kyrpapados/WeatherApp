package com.kyrpapados.weatherapp.util.misc

import androidx.lifecycle.MutableLiveData
import com.kyrpapados.weatherapp.util.misc.Event

object LoadingLiveData : MutableLiveData<Event<Boolean>>()
