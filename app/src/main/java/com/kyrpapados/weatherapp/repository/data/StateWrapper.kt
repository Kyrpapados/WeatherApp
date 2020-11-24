package com.kyrpapados.weatherapp.repository.data

import com.kyrpapados.weatherapp.util.helper.ConnectivityHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class StateWrapper<out T : Any> {

    data class Success<out T : Any>(val data: T) : StateWrapper<T>()

    data class Error(val throwable: Throwable) : StateWrapper<Nothing>()

    data class NoInternet(val message: String = "") : StateWrapper<Nothing>()
}

suspend fun <T : Any> performCall(
    connectivityHelper: ConnectivityHelper? = null,
    function: suspend () -> StateWrapper<T>
): StateWrapper<T> {
    return withContext(Dispatchers.IO) {
        networkCall(connectivityHelper) {
            when {
                connectivityHelper?.isConnected() == false -> StateWrapper.NoInternet()
                else ->  function.invoke()
            }

        }
    }
}

suspend fun <T : Any> networkCall(
    connectivityHelper: ConnectivityHelper? = null,
    call: suspend () -> StateWrapper<T>
): StateWrapper<T> =
    try {
        call.invoke()
    } catch (exception: Exception) {
        when {
            connectivityHelper?.isConnected() == false -> StateWrapper.NoInternet()
            else -> StateWrapper.Error(exception)
        }
    }

suspend fun <T : Any> databaseCall(call: suspend () -> StateWrapper<T>): StateWrapper<T> =
    try {
        call.invoke()
    } catch (exception: Exception) {
        StateWrapper.Error(exception)
    }