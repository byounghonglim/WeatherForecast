package com.byounghong.weatherforecast.data.remote.api

import com.byounghong.weatherforecast.data.remote.data.WeatherForecast
import com.byounghong.weatherforecast.data.remote.data.WeatherLocation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * metaweather 서버 API
 */
interface WeatherForecastApi {

    /**
     * 지정된 단어를 포함하는 지역정보를 가져온다
     */
    @GET("location/search")
    suspend fun getLocationInfo(@Query("query") location:String) : List<WeatherLocation>

    /**
     * 지정된 id를 가진 지역의 날씨 정보를 가져온다
     */
    @GET("location/{woeid}")
    suspend fun getWeatherInfo(@Path("woeid") id:String) : WeatherForecast
}