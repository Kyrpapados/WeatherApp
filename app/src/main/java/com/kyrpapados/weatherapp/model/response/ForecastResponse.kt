package com.kyrpapados.weatherapp.model.response

import com.kyrpapados.weatherapp.model.local.forecast.Forecast
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(@Json(name = "data") val data: Forecast)