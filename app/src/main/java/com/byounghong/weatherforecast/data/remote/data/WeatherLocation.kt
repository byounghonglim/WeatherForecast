package com.byounghong.weatherforecast.data.remote.data

import com.google.gson.annotations.SerializedName

data class WeatherLocation(
	@SerializedName("latt_long") val lattLong: String,					//지역 위도,경도
	@SerializedName("woeid") val woeid: Int,								//지역 ID
	@SerializedName("title") val title: String,							//지역 이름
)
