package com.kyrpapados.weatherapp.repository

import com.kyrpapados.weatherapp.db.AppDatabase
import com.kyrpapados.weatherapp.db.entities.CityData
import com.kyrpapados.weatherapp.repository.data.StateWrapper
import com.kyrpapados.weatherapp.repository.data.databaseCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface DatabaseRepository {

    suspend fun getCities(): StateWrapper<List<CityData>>

    suspend fun saveCity(city: CityData)

    suspend fun deleteCity(areaName: String)

}

class DatabaseRepositoryImpl(private val db: AppDatabase) : DatabaseRepository {

    override suspend fun  getCities(): StateWrapper<List<CityData>> =
        withContext(Dispatchers.IO) {
            databaseCall {
                StateWrapper.Success(db.cityDao().getAllCities())
            }
        }

    override suspend fun saveCity(city: CityData) {
        withContext(Dispatchers.IO) {
            db.cityDao().insertCity(city)
        }
    }

    override suspend fun deleteCity(areaName: String) {
        withContext(Dispatchers.IO) {
            db.cityDao().deleteCity(areaName)
        }
    }

}