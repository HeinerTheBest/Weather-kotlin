package com.mobileapps.umbrella.utilities;

import android.content.Context;

public class SharedPreferences
{

    android.content.SharedPreferences prefs;
    public static final String ZIPCODE = "ZipCode";
    public static final String FARENHEIT = "farenheit";
    public static final String DEGREE = "degree";


    public static final String KEY = "com.mobileapps.umbrella.utilities";




    public void setZipCode(Context context, String zipCode)
    {
         prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
         prefs.edit().putString(ZIPCODE,zipCode).apply();
    }


    public String getZipCode(Context context)
    {
        prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        return prefs.getString(ZIPCODE,"");
    }

    public void setDegree(Context context, String degree)
    {
        prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        prefs.edit().putString(DEGREE,degree).apply();
    }

    public void setDegree(Context context, double degree)
    {

        prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        prefs.edit().putString(DEGREE,String.valueOf(degree)).apply();
    }


    public String getDegree(Context context)
    {
        prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        return prefs.getString(DEGREE,"");
    }


    public void setFarenheit(Context context,Boolean bool)
    {
        prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(FARENHEIT,bool).apply();

    }

    public Boolean isFarenheit(Context context)
    {
        prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        return prefs.getBoolean(FARENHEIT,true);
    }
}
