package com.kyrpapados.weatherapp.ui.main.city.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kyrpapados.weatherapp.R
import com.kyrpapados.weatherapp.model.local.search.SearchItem
import com.kyrpapados.weatherapp.ui.main.city.listeners.OnSearchItemClickListener

class SearchCityAdapter(private var cityList: MutableList<SearchItem>, private val onSearchItemClickListener: OnSearchItemClickListener): RecyclerView.Adapter<SearchCityAdapter.CityViewHolder>() {

    private var selectionList = mutableListOf<SearchItem>()

    class CityViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val searchItemLayout: ConstraintLayout = v.findViewById(R.id.searchItemLayout)
        val searchCityCheckBox: CheckBox = v.findViewById(R.id.searchCityCheckBox)
        val searchCityName: TextView = v.findViewById(R.id.searchCityName)
        val searchCityCountry: TextView = v.findViewById(R.id.searchCityCountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_search_city, parent, false)
        return CityViewHolder(v)
    }

    fun clearAdapter() {
        cityList.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.searchCityName.text = cityList[position].areaName[0].value
        holder.searchCityCountry.text = cityList[position].country[0].value

        holder.searchItemLayout.setOnClickListener {
            holder.searchCityCheckBox.isChecked = !holder.searchCityCheckBox.isChecked
            if(holder.searchCityCheckBox.isChecked && !selectionList.contains(cityList[position])){
                selectionList.add(cityList[position])
            }

            if(!holder.searchCityCheckBox.isChecked && selectionList.contains(cityList[position])){
                selectionList.remove(cityList[position])
            }
            onSearchItemClickListener.onSearchItemClickListener(selectionList)
        }

        holder.searchCityCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            holder.searchCityCheckBox.isChecked = isChecked
            if(holder.searchCityCheckBox.isChecked && !selectionList.contains(cityList[position])){
                selectionList.add(cityList[position])
            }

            if(!holder.searchCityCheckBox.isChecked && selectionList.contains(cityList[position])){
                selectionList.remove(cityList[position])
            }
            onSearchItemClickListener.onSearchItemClickListener(selectionList)
        }

    }

    override fun getItemCount() = cityList.size
}