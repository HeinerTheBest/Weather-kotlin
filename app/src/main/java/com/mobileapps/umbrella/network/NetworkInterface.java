package com.mobileapps.umbrella.network;

import com.mobileapps.umbrella.models.CurrentWeather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.mobileapps.umbrella.network.NetworkHelper.CURRENT_WEATHER_PATH;
import static com.mobileapps.umbrella.network.NetworkHelper.QUERY_KEY;
import static com.mobileapps.umbrella.network.NetworkHelper.QUERY_ZIP;

public interface NetworkInterface
{
    @GET(CURRENT_WEATHER_PATH)
    Observable<CurrentWeather> getCurrentWeather(
            @Query(QUERY_ZIP) String zip,
            @Query(QUERY_KEY) String api_key);



}
