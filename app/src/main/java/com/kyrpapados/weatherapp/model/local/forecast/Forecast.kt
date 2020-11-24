package com.kyrpapados.weatherapp.model.local.forecast

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecast (@Json(name = "current_condition") val current_condition: List<CurrentCondition>,
                     @Json(name = "weather") val weather: List<Weather>)