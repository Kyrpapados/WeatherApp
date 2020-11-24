package com.kyrpapados.weatherapp.ui.main.details

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kyrpapados.weatherapp.R
import com.kyrpapados.weatherapp.base.BaseFragment
import com.kyrpapados.weatherapp.db.entities.CityData
import com.kyrpapados.weatherapp.model.local.forecast.Weather
import com.kyrpapados.weatherapp.util.Statics.Companion.CITY
import com.kyrpapados.weatherapp.util.Statics.Companion.ONE_HOUR
import com.kyrpapados.weatherapp.util.Statics.Companion.SIX_HOUR
import com.kyrpapados.weatherapp.util.Statics.Companion.THREE_HOUR
import com.kyrpapados.weatherapp.util.Statics.Companion.WEATHER_FORECAST
import kotlinx.android.synthetic.main.fragment_city.*
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.loader
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment() {

    private val detailsViewModel by viewModel<DetailsViewModel>()

    private lateinit var cityData: CityData
    private lateinit var weatherForecast: Weather
    private lateinit var detailsAdapter: DetailsAdapter

    override fun getLayout(): Int = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityData = arguments?.getParcelable(CITY)!!
        weatherForecast = arguments?.getParcelable(WEATHER_FORECAST)!!

        detailsAdapter = DetailsAdapter(requireContext(), weatherForecast.hourly)
        detailsAdapter.filterList(ONE_HOUR)
        detailsRecyclerView.adapter = detailsAdapter
    }

    override fun configureViewModel() {
        with(detailsViewModel){
            loadingUI.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    loader.visibility = it
                }
            })

            todayForecastList.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    detailsSwipeLayout.isRefreshing = false
                    detailsAdapter = DetailsAdapter(requireContext(), it[0].hourly)
                    detailsAdapter.filterList(ONE_HOUR)
                    //detailsRecyclerView.adapter = detailsAdapter
                }
            })
        }
    }

    override fun setupViews() {
        detailsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(
                DividerItemDecoration(
                    detailsRecyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun setupListeners() {
        oneHourChip.setOnClickListener {
            detailsAdapter.filterList(ONE_HOUR)
        }

        threeHourChip.setOnClickListener {
            detailsAdapter.filterList(THREE_HOUR)
        }

        sixHourChip.setOnClickListener {
            detailsAdapter.filterList(SIX_HOUR)
        }

        detailsSwipeLayout.setOnRefreshListener {
            detailsSwipeLayout.isRefreshing = true
            detailsViewModel.getTodayForecast(cityData.areaName)
        }
    }

}