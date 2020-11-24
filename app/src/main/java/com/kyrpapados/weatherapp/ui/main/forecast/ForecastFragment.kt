package com.kyrpapados.weatherapp.ui.main.forecast

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.kyrpapados.weatherapp.R
import com.kyrpapados.weatherapp.base.BaseFragment
import com.kyrpapados.weatherapp.db.entities.CityData
import com.kyrpapados.weatherapp.model.local.forecast.Weather
import com.kyrpapados.weatherapp.util.Statics.Companion.CITY
import com.kyrpapados.weatherapp.util.Statics.Companion.WEATHER_FORECAST
import kotlinx.android.synthetic.main.fragment_forecast.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastFragment : BaseFragment(), OnMapReadyCallback, OnForecastClickListener {

    private val forecastViewModel by viewModel<ForecastViewModel>()

    private lateinit var cityData: CityData
    private lateinit var mMap: GoogleMap
    private lateinit var forecastAdapter: ForecastAdapter

    override fun getLayout(): Int = R.layout.fragment_forecast

    override fun onResume() {
        super.onResume()

        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onPause() {
        mapView?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)

        mapView.onResume() // needed to get the map to display immediately

        cityData = arguments?.getParcelable(CITY)!!

        forecastViewModel.getWeatherForecast(cityData.areaName)
    }

    override fun configureViewModel() {
        with(forecastViewModel){
            loadingUI.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    loader.visibility = it
                }
            })

            forecastList.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    forecastAdapter = ForecastAdapter(requireContext(), it, this@ForecastFragment)
                    forecastRecyclerView.adapter = forecastAdapter
                }
            })
        }
    }

    override fun setupViews() {
        mapView.getMapAsync(this)

        forecastRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun setupListeners() {
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isMyLocationButtonEnabled = false
        mMap.uiSettings.isMapToolbarEnabled = false

        mMap.animateCamera(
            CameraUpdateFactory
                .newLatLngZoom(
                    LatLng(
                        cityData.latitude.toDouble(),
                        cityData.longitude.toDouble()
                    ), 12f
                ), 2000, null
        )
    }

    override fun onForecastClickListener(weatherForecast: Weather) {
        findNavController().navigate(R.id.action_forecastFragment_to_detailsFragment,

            Bundle().apply {
                putParcelable(CITY, cityData)
                putParcelable(WEATHER_FORECAST, weatherForecast)
            })
    }

}