package com.mobileapps.umbrella.network;

import com.mobileapps.umbrella.models.currentWeather.CurrentWeather;
import com.mobileapps.umbrella.models.watherofweek.WeatherOfTheWeek;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.mobileapps.umbrella.network.NetworkHelper.CURRENT_WEATHER_PATH;
import static com.mobileapps.umbrella.network.NetworkHelper.QUERY_KEY;
import static com.mobileapps.umbrella.network.NetworkHelper.QUERY_ZIP;
import static com.mobileapps.umbrella.network.NetworkHelper.WEAKLY_WEATHER_PATH;

public interface NetworkInterface
{
    @GET(CURRENT_WEATHER_PATH)
    Observable<CurrentWeather> getCurrentWeather(
            @Query(QUERY_ZIP) String zip,
            @Query(QUERY_KEY) String api_key);

    @GET(WEAKLY_WEATHER_PATH)
    Observable<WeatherOfTheWeek> getWeaklyWeather(
            @Query(QUERY_ZIP) String zip,
            @Query(QUERY_KEY) String api_key);



}
