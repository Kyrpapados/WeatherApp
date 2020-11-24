package com.kyrpapados.weatherapp.repository

import com.kyrpapados.weatherapp.model.response.ForecastResponse
import com.kyrpapados.weatherapp.model.response.SearchCityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDataSource {

    companion object {
        private const val WEATHER_RESULTS = "weather.ashx"
        private const val CITY_SEARCH = "search.ashx"
    }

    /***********************************************
     *
     * Search City
     *
     ********************************************** */

    @GET(CITY_SEARCH)
    suspend fun searchCity(@Query("key") key: String, @Query("q") q: String, @Query("format") format: String,
                           @Query("popular") popular: String): Response<SearchCityResponse>


    @GET(WEATHER_RESULTS)
    suspend fun getForecast(@Query("key") key: String, @Query("q") q: String, @Query("format") format: String,
                            @Query("num_of_days") num_of_days: String, @Query("mca") mca: String, @Query("tp") tp: String): Response<ForecastResponse>

    @GET(WEATHER_RESULTS)
    suspend fun getTodayForecast(@Query("key") key: String, @Query("q") q: String, @Query("format") format: String,
                            @Query("date") date: String, @Query("mca") mca: String, @Query("tp") tp: String): Response<ForecastResponse>


}