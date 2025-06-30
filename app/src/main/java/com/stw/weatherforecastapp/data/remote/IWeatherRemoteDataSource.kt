package com.stw.weatherforecastapp.data.remote

import com.stw.weatherforecastapp.data.remote.models.WeatherResponse
import com.stw.weatherforecastapp.data.remote.service.WeatherApiService
import retrofit2.HttpException
import java.io.IOException

interface IWeatherRemoteDataSource {
    suspend fun getCurrentWeather(cityName: String, apiKey: String): WeatherResponse
}

class WeatherRemoteDataSourceImpl(
    private val apiService: WeatherApiService
) : IWeatherRemoteDataSource {

    override suspend fun getCurrentWeather(cityName: String, apiKey: String): WeatherResponse {
        val response = apiService.getCurrentWeather(cityName, apiKey)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("API response body is null")
        } else {
            throw HttpException(response)
        }
    }
}