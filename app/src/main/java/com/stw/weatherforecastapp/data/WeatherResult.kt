package com.stw.weatherforecastapp.data

sealed class WeatherResult<out T> {
    data class Success<out T>(val data: T) : WeatherResult<T>()
    data class Failure(val exception: Throwable) : WeatherResult<Nothing>()
    object Loading : WeatherResult<Nothing>()
}