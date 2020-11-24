package com.kyrpapados.weatherapp.model.local.forecast

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Weather (@Json(name = "date") val date: String,
                    @Json(name = "maxtempC") val maxtempC: String,
                    @Json(name = "maxtempF") val maxtempF: String,
                    @Json(name = "mintempC") val mintempC: String,
                    @Json(name = "mintempF") val mintempF: String,
                    @Json(name = "avgtempC") val avgtempC: String,
                    @Json(name = "avgtempF") val avgtempF: String,
                    @Json(name = "totalSnow_cm") val totalSnow_cm: String,
                    @Json(name = "sunHour") val sunHour: String,
                    @Json(name = "uvIndex") val uvIndex: String,
                    @Json(name = "hourly") val hourly: List<Hour>): Parcelable