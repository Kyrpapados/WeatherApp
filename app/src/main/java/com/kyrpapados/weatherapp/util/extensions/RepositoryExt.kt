package com.kyrpapados.weatherapp.util.extensions

import com.kyrpapados.weatherapp.repository.data.StateWrapper
import retrofit2.Response
import java.io.IOException

fun <T : Any> Response<T>.toResult(): StateWrapper<T> {
    return when (isSuccessful) {
        true -> StateWrapper.Success(body()!!)
        else -> {
            StateWrapper.Error(IOException(errorBody()?.toString()))
        }
    }
}