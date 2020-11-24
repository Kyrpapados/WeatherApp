package com.kyrpapados.weatherapp.util.extensions

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.lang.StringBuilder

fun parseHour(hour: String): String {
    return when(hour){
        "0" -> {
            "00:00"
        }
        "100","200","300","400","500","600","700","800","900" -> {
            StringBuilder("0$hour").insert(2, ":").toString()
        }
        else -> {
            StringBuilder(hour).insert(2, ":").toString()
        }
    }
}
