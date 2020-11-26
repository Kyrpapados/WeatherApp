package com.kyrpapados.weatherapp.ui.main.details

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.kyrpapados.weatherapp.R
import com.kyrpapados.weatherapp.model.local.forecast.Hour
import com.kyrpapados.weatherapp.util.GlideApp
import com.kyrpapados.weatherapp.util.Statics.Companion.ONE_HOUR
import com.kyrpapados.weatherapp.util.Statics.Companion.SIX_HOUR
import com.kyrpapados.weatherapp.util.Statics.Companion.THREE_HOUR
import com.kyrpapados.weatherapp.util.extensions.parseHour

class DetailsAdapter(private val context: Context) :
    RecyclerView.Adapter<DetailsAdapter.DetailsForecastViewHolder>() {

    private var hourItem = mutableListOf<Hour>()

    class DetailsForecastViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val forecastDetailsDescription: TextView = v.findViewById(R.id.forecastDetailsDescription)
        val forecastDetailsDate: TextView = v.findViewById(R.id.forecastDetailsDate)
        val forecastDetailsTemperatures: TextView = v.findViewById(R.id.forecastDetailsTemperatures)
        val forecastDetailsImg: ImageView = v.findViewById(R.id.forecastDetailsImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsForecastViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_details_forecast, parent, false)
        return DetailsForecastViewHolder(v)
    }

    fun filterList(hour: String, weatherList: List<Hour>){
        hourItem.clear()

        when(hour){
            ONE_HOUR -> {
                hourItem = weatherList.toMutableList()
            }
            THREE_HOUR -> {
                var i = 0

                while (i < weatherList.size){
                    hourItem.add(weatherList[i])

                    i += 3
                }
            }
            SIX_HOUR -> {
                var i = 0

                while (i < weatherList.size){
                    hourItem.add(weatherList[i])

                    i += 6
                }
            }
        }

        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holderForecast: DetailsForecastViewHolder, position: Int) {
        holderForecast.forecastDetailsDescription.text = hourItem[position].weatherDesc[0].value
        holderForecast.forecastDetailsDate.text = parseHour(hourItem[position].time)

        holderForecast.forecastDetailsTemperatures.text =
            "${hourItem[position].tempC} ${context.getString(R.string.celcius)} " +
                    "\n${context.getString(R.string.details_adapter_real_feel)} ${hourItem[position].FeelsLikeC} ${context.getString(R.string.celcius)}"

        val requestOptions = RequestOptions()
        requestOptions.apply {
            signature(ObjectKey(System.currentTimeMillis()))
            circleCrop()
        }

        GlideApp.with(context)
            .asDrawable()
            .load(hourItem[position].weatherIconUrl[0].value)
            .apply(requestOptions)
            .placeholder(R.drawable.ic_icon_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holderForecast.forecastDetailsImg)

    }

    override fun getItemCount() = hourItem.size
}