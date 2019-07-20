package com.mobileapps.umbrella.ui

import com.mobileapps.umbrella.models.currentWeather.CurrentWeather

interface MainViewInterface {
    fun displayWeather(currentWeather : CurrentWeather?)
    fun displayError(s : String)
}
