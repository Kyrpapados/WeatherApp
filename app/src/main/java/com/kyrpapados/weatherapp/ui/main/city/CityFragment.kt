package com.kyrpapados.weatherapp.ui.main.city

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.kyrpapados.weatherapp.R
import com.kyrpapados.weatherapp.base.BaseFragment
import com.kyrpapados.weatherapp.db.entities.CityData
import com.kyrpapados.weatherapp.model.local.search.SearchItem
import com.kyrpapados.weatherapp.ui.main.city.adapter.CityAdapter
import com.kyrpapados.weatherapp.ui.main.city.adapter.SearchCityAdapter
import com.kyrpapados.weatherapp.ui.main.city.listeners.OnCityClickListener
import com.kyrpapados.weatherapp.ui.main.city.listeners.OnSearchItemClickListener
import com.kyrpapados.weatherapp.util.SnackbarUtil
import com.kyrpapados.weatherapp.util.Statics.Companion.CITY
import com.kyrpapados.weatherapp.util.Statics.Companion.WEATHER_FORECAST
import kotlinx.android.synthetic.main.fragment_city.*
import kotlinx.android.synthetic.main.layout_add_city.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityFragment : BaseFragment(), OnCityClickListener, OnSearchItemClickListener {

    private val cityViewModel by viewModel<CityViewModel>()

    private lateinit var cityAdapter: CityAdapter
    private lateinit var searchCityAdapter: SearchCityAdapter

    private lateinit var dialog: MaterialDialog
    private lateinit var customView: View
    private lateinit var selectedCity: CityData

    private var selectedCities = mutableListOf<SearchItem>()

    override fun getLayout(): Int = R.layout.fragment_city

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityViewModel.getCities()
    }

    override fun configureViewModel() {
        with(cityViewModel) {
            loadingUI.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    loader.visibility = it
                }
            })

            retrieveCities.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    cityAdapter = CityAdapter(it, this@CityFragment)

                    cityRecyclerView.adapter = cityAdapter
                }
            })

            searchCity.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    searchCityAdapter = SearchCityAdapter(it.toMutableList(), this@CityFragment)

                    customView.addCityRecyclerView.adapter = searchCityAdapter
                }
            })

            addCity.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    SnackbarUtil.success(
                        cityRecyclerView,
                        if (it) R.string.success else R.string.city_fragment_already_saved
                    )
                    cityViewModel.getCities()
                }
            })

            todayForecastList.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    findNavController().navigate(R.id.action_cityFragment_to_detailsFragment,

                        Bundle().apply {
                            putParcelable(CITY, selectedCity)
                            putParcelable(WEATHER_FORECAST, it[0])
                        })
                }
            })
        }
    }

    override fun setupViews() {
        cityRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }

        dialog = context?.let {
            MaterialDialog(it).title(R.string.city_fragment_search_title)
                .message(R.string.city_fragment_search_message)
                .customView(R.layout.layout_add_city, scrollable = true)
                .positiveButton(R.string.ok, getString(R.string.ok)) {
                    cityViewModel.saveCity(selectedCities)
                }
                .negativeButton(R.string.cancel) {
                    if(::searchCityAdapter.isInitialized) {
                        searchCityAdapter.clearAdapter()
                    }
                }

        }!!

        customView = dialog.getCustomView()

        customView.addCityRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    addCityRecyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            itemAnimator = null
        }
    }

    override fun setupListeners() {
        addCityFab.setOnClickListener {
            dialog.show()
        }

        customView.citySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length > 3) {
                    cityViewModel.searchCity(newText)
                }
                return false
            }
        })

    }

    override fun onCityForecastClickListener(city: CityData) {
        findNavController().navigate(R.id.action_cityFragment_to_forecastFragment,

            Bundle().apply {
                putParcelable(CITY, city)
            })
    }

    override fun onCityHourlyForecastClickListener(city: CityData) {
        selectedCity = city
        cityViewModel.getTodayForecast(cityName = city.areaName)
    }

    override fun onCityDeleteItemClickListener(city: CityData) {
        MaterialDialog(requireContext())
            .title(R.string.city_fragment_delete_dialog)
            .positiveButton(R.string.yes) {
                cityViewModel.deleteCity(city)
            }
            .negativeButton(R.string.no)
            .show()
    }

    override fun onSearchItemClickListener(cities: List<SearchItem>) {
        selectedCities.clear()
        selectedCities = cities.toMutableList()
    }

}