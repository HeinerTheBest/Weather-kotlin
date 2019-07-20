package com.mobileapps.umbrella.models.watherofweek

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherOfTheWeek {

    @SerializedName("cod")
    @Expose
    var cod: String? = null
    @SerializedName("message")
    @Expose
    var message: Double? = null
    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null
    @SerializedName("list")
    @Expose
    var list: kotlin.collections.List<List>? = null
    @SerializedName("city")
    @Expose
    var city: City? = null

}
