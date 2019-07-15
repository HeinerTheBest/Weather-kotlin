package com.mobileapps.umbrella.utilities;

import android.content.Context;
import android.util.Log;

import java.sql.DataTruncation;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Utils
{
    Context context;

    public Utils(Context context)
    {
        this.context = context;
    }

    public String getWindSpeed(double windInMeterForSecond)
    {
        double wind = windInMeterForSecond*2.2369;
        return  "Wind Speed: "+wind +"miles/hr";
    }

    public String getHumedity(Integer humidity)
    {
        DecimalFormat df = new DecimalFormat("###.##");
        return "Humidity: "+df.format(humidity)+"%";
    }

    public String getDegree()
    {
        double temp = Double.valueOf(new SharedPreferences().getDegree(context));
        if(new SharedPreferences().isFarenheit(context))
        {
           double aux = (temp-273.15) * 9/5 + 32 ;
            DecimalFormat df = new DecimalFormat("###.##");
            return df.format(aux);
        }
        else
        {
            double aux = temp - 273.15;
            DecimalFormat df = new DecimalFormat("###.##");
            return df.format(aux);
        }
    }


    public void getDay()
    {
        Timestamp ts=new Timestamp(System.currentTimeMillis());
        Date date=new Date(ts.getTime());
        Log.d("Heiner ","Hellooo "+date);
        System.out.println(date);
    }

}
