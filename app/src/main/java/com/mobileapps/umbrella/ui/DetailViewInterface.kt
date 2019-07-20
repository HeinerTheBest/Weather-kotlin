package com.mobileapps.umbrella.ui

import com.mobileapps.umbrella.models.watherofweek.WeatherOfTheWeek

interface DetailViewInterface {
    fun displayWeather(weatherOfTheWeek: WeatherOfTheWeek?)
    fun displayError(s: String)
}
