package com.kyrpapados.weatherapp.model.local.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchApi (@Json(name = "result") val result: List<SearchItem>)