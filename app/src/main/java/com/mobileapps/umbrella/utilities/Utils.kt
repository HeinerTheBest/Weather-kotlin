package com.mobileapps.umbrella.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

import java.sql.Date
import java.sql.Timestamp
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class Utils(internal var context: Context) {

    val degree: String
        get() {
            val temp : Double?
            temp = java.lang.Double.valueOf(SharedPreferences(context).getDegree()?:"0.0")
            return if (SharedPreferences(context).isFahrenheit()!!) {
                val aux = (temp - 273.15) * 9 / 5 + 32
                val df = DecimalFormat("###.##")
                df.format(aux)
            } else {
                val aux = temp - 273.15
                val df = DecimalFormat("###.##")
                df.format(aux)
            }
        }


    //
    //Hellooo 2019-07-15
    // the day of the week spelled out completely
    //Second Monday
    val currentDayWithDate: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val ts = Timestamp(System.currentTimeMillis())
            Log.d("Heiner ", "time " + ts.time)
            val date = Date(ts.time)
            Log.d("Heiner ", "Hellooo $date")
            println(date)
            val aux = date.toString()


            val simpleDateformat = SimpleDateFormat("EEEE")
            Log.d("Heiner ", "Second " + simpleDateformat.format(date))

            return simpleDateformat.format(date) + ", " + aux
        }

    fun getWindSpeed(windInMeterForSecond: Double): String {
        val wind = windInMeterForSecond * 2.2369
        val df = DecimalFormat("###.##")
        return "Wind Speed: " + df.format(wind) + "miles/hr"
    }

    fun getHumedity(humidity: Int?): String {
        val df = DecimalFormat("###.##")
        return "Humidity: " + df.format(humidity) + "%"
    }

    fun getDegree(temp: Double?): String {
        if (SharedPreferences(context).isFahrenheit()!!) {
            val aux = (temp!! - 273.15) * 9 / 5 + 32
            val df = DecimalFormat("###.##")
            return df.format(aux)
        } else {
            val aux = temp!! - 273.15
            val df = DecimalFormat("###.##")
            return df.format(aux)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getDayOfTheWeek(unix_seconds: Int?): String {
        val date = Date(unix_seconds!! * 1000L)
        val simpleDateformat = SimpleDateFormat("EEEE") // the day of the week spelled out completely
        return simpleDateformat.format(date)
    }


    fun getImageUrl(icon: String): String {
        return "http://openweathermap.org/img/wn/$icon@2x.png"
    }

    fun getJustTIme(dtTxt: String): String {
        return dtTxt.split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[1]
    }
}
