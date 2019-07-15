package com.mobileapps.umbrella.utilities;

import android.content.Context;
import android.util.Log;

import java.sql.DataTruncation;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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
        DecimalFormat df = new DecimalFormat("###.##");
        return  "Wind Speed: "+df.format(wind)+"miles/hr";
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


    public String getCurrentDay()
    {
        Timestamp ts= new Timestamp(System.currentTimeMillis());
        Date date= new Date(ts.getTime());
        Log.d("Heiner ","Hellooo "+date); //Hellooo 2019-07-15
        System.out.println(date);
        String aux = String.valueOf(date);


        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        Log.d("Heiner ","Second "+simpleDateformat.format(date)); //Second Monday

        return simpleDateformat.format(date)+", "+aux;
    }

    public String getImageUrl(String icon)
    {
        String url = "http://openweathermap.org/img/wn/"+icon+"@2x.png";
        return url;
    }
}
