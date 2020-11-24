package com.kyrpapados.weatherapp.model.local.search

import com.kyrpapados.weatherapp.model.local.ItemDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchItem (@Json(name = "latitude") val latitude: String,
                       @Json(name = "longitude") val longitude: String,
                       @Json(name = "population") val population: String,
                       @Json(name = "areaName") val areaName: List<ItemDetail>,
                       @Json(name = "country") val country: List<ItemDetail>,
                       @Json(name = "region") val region: List<ItemDetail>,
                       @Json(name = "weatherUrl") val weatherUrl: List<ItemDetail>)