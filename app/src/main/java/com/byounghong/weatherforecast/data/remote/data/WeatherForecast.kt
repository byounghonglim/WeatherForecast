package com.byounghong.weatherforecast.data.remote.data

import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    @SerializedName("woeid") val woeid: Int,                 //지역 ID
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeatherItem>,         //날씨 정보 리스트
    @SerializedName("title") val title: String,              //지역 이름
)

data class ConsolidatedWeatherItem(
    @SerializedName("applicable_date") val applicableDate: String,          //날씨 정보 날짜
    @SerializedName("weather_state_name") val weatherStateName: String,     //날씨명
    @SerializedName("weather_state_abbr") val weatherStateAbbr: String,     //날씨 아이콘 약자
    @SerializedName("the_temp") val theTemp: Double,                        //온도
    @SerializedName("humidity") val humid: Int,                             //습도
) {
    val temprature: String
        get() = theTemp.toInt().toString()+"℃"

    val humidity: String
        get() = humid.toString()+"%"
}
