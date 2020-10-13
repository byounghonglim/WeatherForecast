package com.byounghong.weatherforecast.ui.main.weatherforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.byounghong.weatherforecast.R
import com.byounghong.weatherforecast.databinding.ItemDetailWeatherHeaderBinding

/**
 * 날씨 정보 리스트의 컬럼명 부분 Layout 및 데이터 바인딩 - RecyclerView의 Header
 */
class WeatherForecastHeaderViewHolder constructor(val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root)
{
    companion object {
        fun from(parent: ViewGroup): WeatherForecastHeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_detail_weather_header, parent, false)
            val binding: ItemDetailWeatherHeaderBinding? = DataBindingUtil.bind(view)

            return WeatherForecastHeaderViewHolder(binding!!)
        }
    }
}