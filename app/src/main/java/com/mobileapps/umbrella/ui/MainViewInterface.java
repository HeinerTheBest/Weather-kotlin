package com.mobileapps.umbrella.ui;

import com.mobileapps.umbrella.models.currentWeather.CurrentWeather;

public interface MainViewInterface
{
    void displayWeather(CurrentWeather currentWeather);
    void displayError(String s);
}
