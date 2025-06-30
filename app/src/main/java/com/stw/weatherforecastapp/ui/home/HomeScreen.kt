package com.stw.weatherforecastapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stw.weatherforecastapp.data.WeatherResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val items by viewModel.weatherUiState.collectAsStateWithLifecycle()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Weather App", style = MaterialTheme.typography.headlineMedium)
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(size = 4.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            var searchText by remember { mutableStateOf("") }
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Enter City Name") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.fetchWeather(searchText)
                }),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
            )
            Button(
                modifier = Modifier
                    .weight(0.3f),
                onClick = {
                    viewModel.fetchWeather(searchText)
                },
                enabled = true,
                shape = RoundedCornerShape(4.dp),
            ) {
                Text("Search")
            }

        }
        Spacer(Modifier.height(16.dp))

        when (items) {
            is WeatherResult.Loading -> {
                CircularProgressIndicator()
                Text("Loading weather data...", modifier = Modifier.padding(top = 8.dp))
            }
            is WeatherResult.Success -> {
                val weatherData = (items as WeatherResult.Success).data
                Text("City: ${weatherData.name}", style = MaterialTheme.typography.titleLarge)
                Text("Temperature: ${weatherData.main.temp}°C")
                Text("Description: ${weatherData.weather.firstOrNull()?.description}")
            }
            is WeatherResult.Failure -> {
                val errorMessage = (items as WeatherResult.Failure).exception.message
                Text("Error: $errorMessage", color = Color.Red)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {


}