package com.mobileapps.umbrella.network

import com.mobileapps.umbrella.models.currentWeather.CurrentWeather
import com.mobileapps.umbrella.models.watherofweek.WeatherOfTheWeek

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

import com.mobileapps.umbrella.network.NetworkHelper.CURRENT_WEATHER_PATH
import com.mobileapps.umbrella.network.NetworkHelper.QUERY_KEY
import com.mobileapps.umbrella.network.NetworkHelper.QUERY_ZIP
import com.mobileapps.umbrella.network.NetworkHelper.WEAKLY_WEATHER_PATH

interface NetworkInterface {
    @GET(CURRENT_WEATHER_PATH)
    fun getCurrentWeather(
            @Query(QUERY_ZIP) zip: String,
            @Query(QUERY_KEY) api_key: String): Observable<CurrentWeather>

    @GET(WEAKLY_WEATHER_PATH)
    fun getWeaklyWeather(
            @Query(QUERY_ZIP) zip: String,
            @Query(QUERY_KEY) api_key: String): Observable<WeatherOfTheWeek>


}
