package com.byounghong.weatherforecast.data.remote.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherForecastApiHelper @Inject constructor(private val apiService:WeatherForecastApi){
    suspend fun getLocationInfo(location:String) = apiService.getLocationInfo(location)
    suspend fun getWeatherInfo(id:String) = apiService.getWeatherInfo(id)
}