package com.byounghong.weatherforecast.data.remote.api

import com.byounghong.weatherforecast.data.remote.data.WeatherForecast
import com.byounghong.weatherforecast.data.remote.data.WeatherLocation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherForecastApi {

    @GET("location/search")
    suspend fun getLocationInfo(@Query("query") location:String) : List<WeatherLocation>

    @GET("location/{woeid}")
    suspend fun getWeatherInfo(@Path("woeid") id:String) : WeatherForecast
}