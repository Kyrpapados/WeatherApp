package com.kyrpapados.weatherapp.ui.main.city.listeners

import com.kyrpapados.weatherapp.model.local.search.SearchItem

interface OnSearchItemClickListener {

    fun onSearchItemClickListener(cities: List<SearchItem>)

}