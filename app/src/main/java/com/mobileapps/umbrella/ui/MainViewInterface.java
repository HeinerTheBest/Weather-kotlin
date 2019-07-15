package com.mobileapps.umbrella.ui;

import com.mobileapps.umbrella.models.CurrentWeather;

public interface MainViewInterface
{
    void displayWeather(CurrentWeather currentWeather);
    void displayError(String s);
}
