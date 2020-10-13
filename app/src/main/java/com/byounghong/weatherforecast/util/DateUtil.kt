package com.byounghong.weatherforecast.util

import java.text.SimpleDateFormat
import java.util.*


/**
 * 패턴에 따라 오늘 날짜를 리턴
 */
fun getToday(pattern: String): String =
    SimpleDateFormat(pattern)
        .format(Calendar.getInstance().time)

/**
 * 패턴에 따라 내일 날짜를 리턴
 */
fun getTomorrow(pattern: String?): String =
    SimpleDateFormat(pattern)
        .format(Calendar.getInstance().apply {
            this.add(Calendar.DATE, 1)}.time)
