package com.byounghong.weatherforecast.data.remote.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStatus @Inject constructor(@ApplicationContext private val context: Context){
    fun isNetworkConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork =
            connectivityManager.getNetworkCapabilities(
                connectivityManager.activeNetwork ?: return false) ?: return false

        return when {
            activeNetwork.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(
                NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}