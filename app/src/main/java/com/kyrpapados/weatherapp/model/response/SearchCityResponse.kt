package com.kyrpapados.weatherapp.model.response

import com.kyrpapados.weatherapp.model.local.search.SearchApi
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchCityResponse(@Json(name = "search_api") val search_api: SearchApi)