package com.kyrpapados.weatherapp.model.local.forecast

import android.os.Parcelable
import com.kyrpapados.weatherapp.model.local.ItemDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Hour (@Json(name = "time") val time: String,
                 @Json(name = "tempC") val tempC: String,
                 @Json(name = "tempF") val tempF: String,
                 @Json(name = "weatherCode") val weatherCode: String,
                 @Json(name = "weatherIconUrl") val weatherIconUrl: List<ItemDetail>,
                 @Json(name = "weatherDesc") val weatherDesc: List<ItemDetail>,
                 @Json(name = "humidity") val humidity: String,
                 @Json(name = "HeatIndexC") val HeatIndexC: String,
                 @Json(name = "HeatIndexF") val HeatIndexF: String,
                 @Json(name = "FeelsLikeC") val FeelsLikeC: String,
                 @Json(name = "FeelsLikeF") val FeelsLikeF: String):  Parcelable