package com.kyrpapados.weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kyrpapados.weatherapp.db.dao.CityDao
import com.kyrpapados.weatherapp.db.entities.CityData

@Database(
    entities = [
        CityData::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

}