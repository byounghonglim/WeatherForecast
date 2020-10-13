package com.byounghong.weatherforecast.util

import android.view.View
import androidx.databinding.BindingAdapter


/**
 * LivieData - Boolean에 따른 View의 Visible BindingAdapter
 */
@BindingAdapter("visible")
fun setVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

