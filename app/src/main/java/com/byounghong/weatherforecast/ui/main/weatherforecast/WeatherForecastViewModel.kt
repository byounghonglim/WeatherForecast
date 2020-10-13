package com.byounghong.weatherforecast.ui.main.weatherforecast

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.byounghong.weatherforecast.data.remote.data.WeatherLocation
import com.byounghong.weatherforecast.data.remote.module.NetworkStatus
import com.byounghong.weatherforecast.data.repository.WeatherForecastRepository
import com.byounghong.weatherforecast.logger.Logger
import com.byounghong.weatherforecast.util.ListLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton

/**
 * 날씨 예보 화면(Fragment)에서 사용되는 데이터 및 로직 처리
 */
@Singleton
class WeatherForecastViewModel @ViewModelInject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val networkStatus: NetworkStatus,
    private val logger: Logger,
    @Assisted private val savedStateHandle: SavedStateHandle)
    : ViewModel() {

    //서버의 지역 정보를 담는 리스트
    private val _weatherLocationlist = MutableLiveData<List<WeatherLocation>>()
    private val weatherLocationlist:LiveData<List<WeatherLocation>> = _weatherLocationlist

    //서버의 날씨 정보를 담는 리스트
    private val _weatherForecastResponseList = ListLiveData<WeatherForcastListItem>()
    private val weatherForecastResponseList: ListLiveData<WeatherForcastListItem> = _weatherForecastResponseList

    //서버와의 통신 유무
    var isFinishedGetWeatherForecast: MutableLiveData<Boolean> = MutableLiveData(false)

    //날씨 정보 리스트의 Adapter
    val weatherForecastListAdapter = WeatherForecastListAdapter()

    //날씨 정보가 필요한 기본 지역 이름
    lateinit var locationName:String

    fun getLocationInfo() {
        getLocationInfo(locationName)
    }

    /**
     * 서버로 부터 지역 이름을 포함하는 지역 정보를 가져온다
     */
    fun getLocationInfo(name: String) {
        isFinishedGetWeatherForecast.value = false
        locationName = name;

        if(!networkStatus.isNetworkConnected()){
            isFinishedGetWeatherForecast.value = true
            return
        }

        viewModelScope.launch {
            try {
                _weatherLocationlist.value =
                    weatherForecastRepository.getWeatherLocation(name)
                if(_weatherLocationlist.value!!.isNotEmpty()){
                    getWeatherInfo(_weatherLocationlist.value!!)
                }
            } catch (exception: Exception) {
                isFinishedGetWeatherForecast.value = true
                exception.message?.let { logger.log(it) }
            }
        }
    }

    /**
     * 서버로 부터 리스트에 포함된 모든 지역 ID에 해당하는 날씨 예보를 가져온다
     */
    private fun getWeatherInfo(weatherLocations: List<WeatherLocation>) = runBlocking {
        _weatherForecastResponseList.clear(true)
        weatherLocations.map { locationInfo -> viewModelScope.async(Dispatchers.IO) { getWeatherInfo(
            locationInfo.woeid.toString()
        ) } }
            .map { it.await() }
    }

    /**
     * 서버로 부터 지역 ID에 해당하는 날씨 예보를 가져온다
     */
    private suspend fun getWeatherInfo(id: String) {
        viewModelScope.launch {
            try {
                val weatherForecast = weatherForecastRepository.getWeatherForecast(id)
                _weatherForecastResponseList.value?.add(WeatherForcastListItem.WeatherInfoItem(weatherForecast))

                if(weatherLocationlist.value!!.size == _weatherForecastResponseList.value?.size){
                    _weatherForecastResponseList.value?.add(0, WeatherForcastListItem.Header)
                    weatherForecastListAdapter.submitList(weatherForecastResponseList.value)
                    isFinishedGetWeatherForecast.value = true
                }
            } catch (exception: Exception) {
                isFinishedGetWeatherForecast.value = true
                exception.message?.let { logger.log(it) }
            }
        }
    }
}