package com.byounghong.weatherforecast.ui.main.weatherforecast

import com.byounghong.weatherforecast.data.remote.data.WeatherForecast

/**
 * 날씨 예보 리스트에서 Header(컬럼명) 부분을 위해서 sealed class로 Header와 Item으로 관리
 */
sealed class WeatherForcastListItem {
    abstract val id: Int
    data class WeatherInfoItem(val weatherForecast: WeatherForecast): WeatherForcastListItem()      {
        override val id = weatherForecast.woeid
    }

    object Header: WeatherForcastListItem() {
        override val id = -1
    }
}