package com.byounghong.weatherforecast.data.remote.data

import com.google.gson.annotations.SerializedName

data class WeatherLocation(
	@SerializedName("latt_long") val lattLong: String,
	@SerializedName("woeid") val woeid: Int,
	@SerializedName("title") val title: String,
	@SerializedName("location_type") val locationType: String
)
