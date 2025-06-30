package com.stw.weatherforecastapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("base")
    val base: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt")
    val dt: Long, // timestamp
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Long, // shift in seconds from UTC
    @SerializedName("id")
    val id: Long, // city ID
    @SerializedName("name")
    val name: String, // city name
    @SerializedName("cod")
    val cod: Int // internal parameter
)

data class Coord(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double
)

data class Weather(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)

data class Main(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feels_like: Double,
    @SerializedName("temp_min")
    val temp_min: Double,
    @SerializedName("temp_max")
    val temp_max: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("sea_level")
    val sea_level: Int?, // Optional, as it might not always be present or can be null
    @SerializedName("grnd_level")
    val grnd_level: Int? // Optional
)

data class Wind(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double? // Optional
)

data class Clouds(
    @SerializedName("all")
    val all: Int
)

data class Sys(
    @SerializedName("country")
    val country: String,
    @SerializedName("sunrise")
    val sunrise: Long, // timestamp
    @SerializedName("sunset")
    val sunset: Long // timestamp
)