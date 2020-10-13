package com.byounghong.weatherforecast.data.repository

import com.byounghong.weatherforecast.data.remote.api.WeatherForecastApiHelper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 날씨 예보와 관련된 Repository (현재는 네트워크 관련된 부분만 있음)
 */
@Singleton
class WeatherForecastRepository @Inject constructor(
    private val weatherForecastApiHelper:WeatherForecastApiHelper) {

    suspend fun getWeatherLocation(location:String) =
        weatherForecastApiHelper.getLocationInfo(location)

    suspend fun getWeatherForecast(id:String) =
        weatherForecastApiHelper.getWeatherInfo(id)
}