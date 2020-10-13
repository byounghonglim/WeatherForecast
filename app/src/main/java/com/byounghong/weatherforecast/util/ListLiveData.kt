package com.byounghong.weatherforecast.util

import androidx.lifecycle.MutableLiveData

/**
 * LiveData 내 ArrayList에 데이터를 처리하기 위해 Custom ListLiveData
 */
class ListLiveData<T> : MutableLiveData<ArrayList<T>>() {
    init {
        value = ArrayList()
    }

    fun add(item: T) {
        val items: ArrayList<T>? = value
        items!!.add(item)
        value = items
    }

    fun add(index:Int, item: T) {
        val items: ArrayList<T>? = value
        items!!.add(index, item)
        value = items
    }

    fun addAll(list: List<T>) {
        val items: ArrayList<T>? = value
        items!!.addAll(list)
        value = items
    }

    fun clear(notify: Boolean) {
        val items: ArrayList<T>? = value
        items!!.clear()
        if (notify) {
            value = items
        }
    }

    fun remove(item: T) {
        val items: ArrayList<T>? = value
        items!!.remove(item)
        value = items
    }

    fun notifyChange() {
        val items: ArrayList<T>? = value
        value = items
    }
}