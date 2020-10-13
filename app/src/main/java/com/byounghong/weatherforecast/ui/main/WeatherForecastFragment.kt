package com.byounghong.weatherforecast.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.byounghong.weatherforecast.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherForecastFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherForecastFragment()
    }

    private lateinit var viewModel: WeatherForecastViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.weatherforecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeatherForecastViewModel::class.java)
        // TODO: Use the ViewModel
    }

}