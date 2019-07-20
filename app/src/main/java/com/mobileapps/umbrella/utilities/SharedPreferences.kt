package com.mobileapps.umbrella.utilities

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context, private var prefs : SharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE) ) {


    fun setZipCode(zipCode : String) {
        prefs.edit().putString(ZIP_CODE, zipCode).apply()
    }


    fun getZipCode(): String? {
        return prefs.getString(ZIP_CODE, "")
    }

    fun setDegree(degree: String) {
        prefs.edit().putString(DEGREE, degree).apply()
    }

    fun setDegree(degree: Double) {

        prefs.edit().putString(DEGREE, degree.toString()).apply()
    }


    fun getDegree(): String? {
        return prefs.getString(DEGREE, "")
    }


    fun setFahrenheit(bool: Boolean?) {
        prefs.edit().putBoolean(FAHRENHEIT, bool!!).apply()

    }

    fun isFahrenheit(): Boolean? {
        return prefs.getBoolean(FAHRENHEIT, true)
    }

    companion object {
        const val ZIP_CODE = "ZipCode"
        const val FAHRENHEIT = "fahrenheit"
        const val DEGREE = "degree"


        const val KEY = "com.mobile's.umbrella.utilities"
    }
}
