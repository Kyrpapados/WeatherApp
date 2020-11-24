package com.kyrpapados.weatherapp.util.helper

import android.content.SharedPreferences

@Suppress("unused")
class PreferencesHelper(private val preferences: SharedPreferences) {

    fun putString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return preferences.getString(key, "")
    }

    fun putBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun putInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return preferences.getInt(key, -1)
    }

    fun putFloat(key: String, value: Float) {
        preferences.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String): Float {
        return preferences.getFloat(key, -1f)
    }

    fun putLong(key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun getLong(key: String): Long {
        return preferences.getLong(key, -1L)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

}