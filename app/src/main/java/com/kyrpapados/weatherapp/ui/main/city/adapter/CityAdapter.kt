package com.kyrpapados.weatherapp.ui.main.city.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.kyrpapados.weatherapp.R
import com.kyrpapados.weatherapp.db.entities.CityData
import com.kyrpapados.weatherapp.ui.main.city.listeners.OnCityClickListener

class CityAdapter(private val onCityClickListener: OnCityClickListener): RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var cityList = mutableListOf<CityData>()

    fun setData(cities: List<CityData>){
        cityList.clear()
        cityList = cities.toMutableList()
        notifyDataSetChanged()
    }

    class CityViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val cityName: TextView = v.findViewById(R.id.cityItemName)
        val cityCountry: TextView = v.findViewById(R.id.cityItemCountry)
        val forecastBtn: MaterialButton = v.findViewById(R.id.cityForecastButton)
        val detailsBtn: MaterialButton = v.findViewById(R.id.cityDetailsButton)
        val cityItemDelete: ImageView = v.findViewById(R.id.cityItemDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(v)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.cityName.text = cityList[position].areaName
        holder.cityCountry.text = cityList[position].country


        holder.forecastBtn.setOnClickListener {
            onCityClickListener.onCityForecastClickListener(cityList[position])
        }

        holder.detailsBtn.setOnClickListener {
            onCityClickListener.onCityHourlyForecastClickListener(cityList[position])
        }

        holder.cityItemDelete.setOnClickListener {
            onCityClickListener.onCityDeleteItemClickListener(cityList[position])
        }
    }

    override fun getItemCount() = cityList.size
}