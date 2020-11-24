package com.kyrpapados.weatherapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {

    abstract fun getLayout(): Int
    abstract fun configureViewModel()
    abstract fun setupViews()
    abstract fun setupListeners()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureViewModel()
        setupViews()
        setupListeners()
    }

}