package com.byounghong.weatherforecast.logger

import android.content.Context
import android.util.Log
import javax.inject.Inject

/**
 * 로그 설정
 */
class Logger @Inject constructor(private val context: Context) {
    fun log(msg : String) {
        Log.d(context.packageName, msg)
    }
}