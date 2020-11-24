package com.kyrpapados.weatherapp.model.local

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ItemDetail (@Json(name = "value") val value: String): Parcelable