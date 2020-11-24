package com.kyrpapados.weatherapp.util

import android.graphics.Color
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.kyrpapados.weatherapp.R

object SnackbarUtil {

    private var snackbar: Snackbar? = null
    fun success(view: View, @StringRes message: Int = R.string.success) {
        snackbar?.dismiss()
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(android.R.string.ok) { dismiss() }
            setActionTextColor(ContextCompat.getColor(view.context, R.color.alert_success_text))
            setTextColor(ContextCompat.getColor(view.context, R.color.alert_success_text))
            setBackgroundTint(ContextCompat.getColor(view.context, R.color.alert_success_bg))
//            val tv = getView().findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
//            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.animated_check, 0, 0, 0)
            show()
        }
    }

    fun error(view: View, @StringRes message: Int = R.string.try_again) {
        snackbar?.dismiss()
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(android.R.string.ok) { dismiss() }
            setActionTextColor(ContextCompat.getColor(view.context, R.color.alert_warning_text))
            setTextColor(ContextCompat.getColor(view.context, R.color.alert_warning_text))
            setBackgroundTint(ContextCompat.getColor(view.context, R.color.alert_warning_bg))
//            val tv = getView().findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
//            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check_mark, 0, 0, 0)
            show()
        }
    }
}