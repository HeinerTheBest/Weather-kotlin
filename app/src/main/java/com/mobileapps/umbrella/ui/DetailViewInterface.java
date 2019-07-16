package com.mobileapps.umbrella.ui;

import com.mobileapps.umbrella.models.watherofweek.WeatherOfTheWeek;

public interface DetailViewInterface
{
    void displayWeather(WeatherOfTheWeek weatherOfTheWeek);
    void displayError(String s);
}
