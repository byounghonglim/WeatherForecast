package com.byounghong.weatherforecast.ui.main.weatherforecast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.byounghong.weatherforecast.R
import com.byounghong.weatherforecast.databinding.FragmentWeatherforecastBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_weatherforecast.*

/**
 * 날씨 정보 리스트를 보여주는 Fragment
 */
@AndroidEntryPoint
class WeatherForecastFragment : Fragment(R.layout.fragment_weatherforecast) {

    private val weatherForecastViewModel : WeatherForecastViewModel by viewModels()

    private val LOCATION_NAME = "se"        //"se" 단어를 Fix

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObserver()
        recyclerView.adapter = weatherForecastViewModel.weatherForecastListAdapter
        weatherForecastViewModel.getLocationInfo(LOCATION_NAME)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentWeatherforecastBinding.bind(view).apply{
            viewModel = weatherForecastViewModel
            lifecycleOwner=this@WeatherForecastFragment
        }
    }

    override fun onResume() {
        super.onResume()
        refreshLayout.setOnRefreshListener {
            weatherForecastViewModel.getLocationInfo()
        }
    }

    private fun initObserver() {
        weatherForecastViewModel.isFinishedGetWeatherForecast.observe(viewLifecycleOwner, { isFinish ->
            refreshLayout(isFinish)
        })
    }

    /**
     * 네트워크 통신 중에 화면 갱신 메소드(초기 화면과 SwipeRefreshLayout에 따라서 ProgressBar 동작이 상이함
     */
    private fun refreshLayout( isFinishGetData:Boolean){
        if(isFinishGetData){
            if(refreshLayout.isRefreshing) {
                refreshLayout.isRefreshing = false
            } else{
                progressBar.visibility = View.GONE
            }
        } else {
            if(!refreshLayout.isRefreshing){
                progressBar.visibility = View.VISIBLE
            }
        }
    }
}


