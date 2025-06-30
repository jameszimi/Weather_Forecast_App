package com.stw.weatherforecastapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stw.weatherforecastapp.data.remote.models.WeatherResponse
import com.stw.weatherforecastapp.data.repository.IWeatherAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.stw.weatherforecastapp.data.WeatherResult
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherAppRepository: IWeatherAppRepository
) : ViewModel() {


    private val _weatherUiState = MutableStateFlow<WeatherResult<WeatherResponse>>(WeatherResult.Loading) // Default to loading or an initial idle state
    val weatherUiState: StateFlow<WeatherResult<WeatherResponse>> = _weatherUiState.asStateFlow()

    fun fetchWeather(cityName: String) {
        viewModelScope.launch {
            weatherAppRepository.getWeather(cityName)
                .collect { result ->
                    _weatherUiState.value = result
                }
        }
    }




}

