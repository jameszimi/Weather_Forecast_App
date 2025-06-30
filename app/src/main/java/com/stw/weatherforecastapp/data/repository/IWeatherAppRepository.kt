package com.stw.weatherforecastapp.data.repository
import com.stw.weatherforecastapp.BuildConfig
import com.stw.weatherforecastapp.data.WeatherResult
import com.stw.weatherforecastapp.data.remote.IWeatherRemoteDataSource
import com.stw.weatherforecastapp.data.remote.models.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

interface IWeatherAppRepository {
    fun getWeather(cityName: String): Flow<WeatherResult<WeatherResponse>>
}

class WeatherAppRepositoryImpl(
    // Now only depends on the remote data source
    private val remoteDataSource: IWeatherRemoteDataSource
) : IWeatherAppRepository {

    private val API_KEY = BuildConfig.OPEN_WEATHER_API_KEY

    override fun getWeather(cityName: String): Flow<WeatherResult<WeatherResponse>> = flow {
        emit(WeatherResult.Loading) // Always emit Loading first
        try {
            val remoteData = remoteDataSource.getCurrentWeather(cityName, API_KEY)
            emit(WeatherResult.Success(remoteData))
        } catch (e: HttpException) {
            // Handle HTTP errors
            val errorBody = e.response()?.errorBody()?.string()
            val errorMessage = "API Error: ${e.code()} - ${errorBody ?: e.message()}"
            emit(WeatherResult.Failure(Exception(errorMessage)))
        } catch (e: IOException) {
            // Handle network connectivity issues
            val errorMessage = "Network Error: Please check your internet connection. ${e.message}"
            emit(WeatherResult.Failure(Exception(errorMessage)))
        } catch (e: Exception) {
            // Handle any other unexpected errors
            emit(WeatherResult.Failure(e))
        }
    }
}