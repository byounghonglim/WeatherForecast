package com.byounghong.weatherforecast.ui.main.weatherforecast

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.byounghong.weatherforecast.databinding.ItemDetailWeatherListBinding
import com.byounghong.weatherforecast.util.getToday
import com.byounghong.weatherforecast.util.getTomorrow

/**
 * 날씨 예보 리스트(RecyclerView)의 Adapter
 */
class WeatherForecastListAdapter : ListAdapter<WeatherForcastListItem, RecyclerView.ViewHolder>(Companion) {
    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1

    /**
     * ListAdapter & DiffUtil로 인해 변경점이 있을 경우에만 해당 부분 변경
     */
    companion object : DiffUtil.ItemCallback<WeatherForcastListItem>() {
        override fun areItemsTheSame(oldItem: WeatherForcastListItem, newItem: WeatherForcastListItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: WeatherForcastListItem, newItem: WeatherForcastListItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> WeatherForecastHeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> WeatherForecastItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherForecastItemViewHolder -> {
                val dataItem = getItem(position) as WeatherForcastListItem.WeatherInfoItem
                val weatherForecast = dataItem.weatherForecast
                val itemBinding = holder.binding as ItemDetailWeatherListBinding

                itemBinding.weatherForecast = weatherForecast
                weatherForecast.consolidatedWeather.forEach{
                    if(it.applicableDate == getToday("yyyy-MM-dd"))
                        itemBinding.todayWeather = it
                    if(it.applicableDate == getTomorrow("yyyy-MM-dd"))
                        itemBinding.tomorrowWeather = it
                }

                itemBinding.executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is WeatherForcastListItem.Header -> ITEM_VIEW_TYPE_HEADER
            is WeatherForcastListItem.WeatherInfoItem -> ITEM_VIEW_TYPE_ITEM
        }
    }
}

