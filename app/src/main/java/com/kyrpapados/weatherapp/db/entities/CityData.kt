package com.kyrpapados.weatherapp.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "CityData")
@Parcelize
data class CityData (

        @PrimaryKey
        @ColumnInfo(name = "areaName")
        var areaName: String,

        @ColumnInfo(name = "country")
        var country: String,

        @ColumnInfo(name = "region")
        var region: String,

        @ColumnInfo(name = "latitude")
        var latitude: String,

        @ColumnInfo(name = "longitude")
        var longitude: String

): Parcelable {
        constructor() : this("", "","","","")
}