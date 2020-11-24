package com.kyrpapados.weatherapp.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.kyrpapados.weatherapp.R
import com.kyrpapados.weatherapp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun getLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.main_nav_host_fragment)

        val appConfiguration = AppBarConfiguration(setOf(R.id.cityFragment))

        setSupportActionBar(mainToolbar)

        setupActionBarWithNavController(navController, appConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.cityFragment -> finish()
            else -> super.onBackPressed()
        }
    }

}