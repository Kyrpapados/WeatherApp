package com.kyrpapados.weatherapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kyrpapados.weatherapp.db.entities.CityData

@Dao
interface CityDao {

    @Query("DELETE FROM CityData WHERE areaName == (:areaName)")
    suspend fun deleteCity(areaName: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(cityData: CityData)

    @Query("Select * FROM CityData")
    suspend fun getAllCities(): List<CityData>

}