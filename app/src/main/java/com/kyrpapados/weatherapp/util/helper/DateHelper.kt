package com.kyrpapados.weatherapp.util.helper

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DateHelper {

    fun getTodayDate(): String {
        return try {
            val today = Calendar.getInstance().time
            DateFormat.format("yyyy-MM-dd", today).toString()
        } catch (ex: Exception) {
            "n/a"
        }
    }

    fun formatDate(datePattern: String, date: String, postFormatString: String): String {
        val postFormat = SimpleDateFormat(postFormatString, Locale.getDefault())
        val preFormatString = SimpleDateFormat(datePattern, Locale.getDefault())
        var result: String
        val convertedDate: Date
        try {
            convertedDate = preFormatString.parse(date)
            result = postFormat.format(convertedDate)
        } catch (e: Exception) {
            e.printStackTrace()
            result = "n/a"
        }

        return result
    }
}