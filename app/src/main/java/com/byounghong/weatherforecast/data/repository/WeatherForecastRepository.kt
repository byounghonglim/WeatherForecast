package com.byounghong.weatherforecast.data.repository

import com.byounghong.weatherforecast.data.remote.api.WeatherForecastApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherForecastRepository @Inject constructor(
    private val weatherForecastApiHelper:WeatherForecastApiHelper) {

    suspend fun getWeatherLocation(location:String) =
        weatherForecastApiHelper.getLocationInfo(location)

    suspend fun getWeatherForecast(id:String) =
        weatherForecastApiHelper.getWeatherInfo(id)
}