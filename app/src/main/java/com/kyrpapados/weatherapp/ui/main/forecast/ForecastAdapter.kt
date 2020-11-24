package com.kyrpapados.weatherapp.ui.main.forecast

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.kyrpapados.weatherapp.R
import com.kyrpapados.weatherapp.model.local.forecast.Weather
import com.kyrpapados.weatherapp.util.GlideApp
import com.kyrpapados.weatherapp.util.helper.DateHelper

class ForecastAdapter(
    private val context: Context,
    private val weatherList: List<Weather>,
    private val onForecastClickListener: OnForecastClickListener
) : RecyclerView.Adapter<ForecastAdapter.CityViewHolder>() {

    class CityViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val forecastDetailsDescription: TextView = v.findViewById(R.id.forecastDetailsDescription)
        val forecastDetailsDate: TextView = v.findViewById(R.id.forecastDetailsDate)
        val forecastDetailsTemperatures: TextView = v.findViewById(R.id.forecastDetailsTemperatures)
        val forecastDetailsImg: ImageView = v.findViewById(R.id.forecastDetailsImg)
        val forecastLayout: CardView = v.findViewById(R.id.forecastLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_details_forecast, parent, false)
        return CityViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.forecastDetailsDate.text = DateHelper().formatDate(
            "yyyy-MM-dd",
            weatherList[position].date, "dd/MM"
        )
        holder.forecastDetailsDescription.text =
            weatherList[position].hourly[weatherList[position].hourly.size / 2].weatherDesc[0].value

        var minC = 100.00
        var maxC = -100.00

        for (temperature in weatherList[position].hourly) {
            if (temperature.tempC.toDouble() <= minC) {
                minC = temperature.tempC.toDouble()
            }

            if (temperature.tempC.toDouble() >= maxC) {
                maxC = temperature.tempC.toDouble()
            }
        }

        holder.forecastDetailsTemperatures.text =
            "$minC ${context.getString(R.string.celcius)} / $maxC ${context.getString(R.string.celcius)}"

        val requestOptions = RequestOptions()
        requestOptions.apply {
            signature(ObjectKey(System.currentTimeMillis()))
            circleCrop()
        }

        GlideApp.with(context)
            .asDrawable()
            .load(weatherList[position].hourly[weatherList[position].hourly.size / 2].weatherIconUrl[0].value)
            .apply(requestOptions)
            .placeholder(R.drawable.ic_icon_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(holder.forecastDetailsImg)


        holder.forecastLayout.setOnClickListener {
            onForecastClickListener.onForecastClickListener(weatherList[position])
        }
    }

    override fun getItemCount() = weatherList.size
}