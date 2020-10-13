package com.byounghong.weatherforecast.data.remote.data

import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    @SerializedName("woeid") val woeid: Int,
    @SerializedName("consolidated_weather") val consolidatedWeather: List<ConsolidatedWeatherItem>,
    @SerializedName("title") val title: String,
)

data class ConsolidatedWeatherItem(
    @SerializedName("applicable_date") val applicableDate: String,
    @SerializedName("weather_state_name") val weatherStateName: String,
    @SerializedName("weather_state_abbr") val weatherStateAbbr: String,
    @SerializedName("the_temp") val theTemp: Double,
    @SerializedName("humidity") val humid: Int,
) {
    val temprature: String
        get() = theTemp.toInt().toString()+"℃"

    val humidity: String
        get() = humid.toString()+"%"
}
