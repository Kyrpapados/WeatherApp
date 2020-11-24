package com.kyrpapados.weatherapp.model.local.forecast

import com.kyrpapados.weatherapp.model.local.ItemDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentCondition (@Json(name = "observation_time") val observation_time: String,
                             @Json(name = "temp_C") val temp_C: String,
                             @Json(name = "temp_F") val temp_F: String,
                             @Json(name = "weatherCode") val weatherCode: String,
                             @Json(name = "weatherIconUrl") val weatherIconUrl: List<ItemDetail>,
                             @Json(name = "weatherDesc") val weatherDesc: List<ItemDetail>,
                             @Json(name = "windspeedMiles") val windspeedMiles: String,
                             @Json(name = "windspeedKmph") val windspeedKmph: String,
                             @Json(name = "winddirDegree") val winddirDegree: String,
                             @Json(name = "winddir16Point") val winddir16Point: String,
                             @Json(name = "precipMM") val precipMM: String,
                             @Json(name = "precipInches") val precipInches: String,
                             @Json(name = "humidity") val humidity: String,
                             @Json(name = "visibility") val visibility: String,
                             @Json(name = "visibilityMiles") val visibilityMiles: String,
                             @Json(name = "pressure") val pressure: String,
                             @Json(name = "pressureInches") val pressureInches: String,
                             @Json(name = "cloudcover") val cloudcover: String,
                             @Json(name = "FeelsLikeC") val FeelsLikeC: String,
                             @Json(name = "FeelsLikeF") val FeelsLikeF: String,
                             @Json(name = "uvIndex") val uvIndex: String)