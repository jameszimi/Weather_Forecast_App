package com.stw.weatherforecastapp.data.di

import com.stw.weatherforecastapp.data.remote.IWeatherRemoteDataSource
import com.stw.weatherforecastapp.data.remote.RetrofitClient
import com.stw.weatherforecastapp.data.remote.WeatherRemoteDataSourceImpl
import com.stw.weatherforecastapp.data.remote.service.WeatherApiService
import com.stw.weatherforecastapp.data.repository.IWeatherAppRepository
import com.stw.weatherforecastapp.data.repository.WeatherAppRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApiService(): WeatherApiService {
        return RetrofitClient.weatherApiService
    }

    @Provides
    @Singleton
    fun provideWeatherRemoteDataSource(weatherApiService: WeatherApiService): IWeatherRemoteDataSource {
        return WeatherRemoteDataSourceImpl(weatherApiService)
    }

    @Provides
    @Singleton
    fun provideWeatherAppRepository(
        remoteDataSource: IWeatherRemoteDataSource
    ): IWeatherAppRepository {
        return WeatherAppRepositoryImpl(remoteDataSource)
    }
}