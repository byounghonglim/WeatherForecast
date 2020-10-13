package com.byounghong.weatherforecast.ui.main.weatherforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.byounghong.weatherforecast.R
import com.byounghong.weatherforecast.databinding.ItemDetailWeatherListBinding

/**
 * 날씨 정보 리스트의 내용 부분 Layout 및 데이터 바인딩 - RecyclerView의 Item
 */
class WeatherForecastItemViewHolder constructor(val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root)
{
    companion object {
        fun from(parent: ViewGroup): WeatherForecastItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_detail_weather_list, parent, false)
            val binding: ItemDetailWeatherListBinding? = DataBindingUtil.bind(view)

            return WeatherForecastItemViewHolder(binding!!)
        }
    }
}