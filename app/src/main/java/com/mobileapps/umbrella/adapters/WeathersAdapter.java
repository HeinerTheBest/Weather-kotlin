package com.mobileapps.umbrella.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mobileapps.umbrella.R;
import com.mobileapps.umbrella.utilities.SharedPreferences;
import com.mobileapps.umbrella.utilities.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeathersAdapter extends RecyclerView.Adapter<WeathersAdapter.ViewHolder>
{
    List<com.mobileapps.umbrella.models.watherofweek.List> weatherOfTheWeek;
    Context context;

    public WeathersAdapter(List<com.mobileapps.umbrella.models.watherofweek.List> weatherOfTheWeek, Context context) {
        this.weatherOfTheWeek = weatherOfTheWeek;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_weather,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Utils utils = new Utils(context);
        SharedPreferences sharedPreferences = new SharedPreferences();
        com.mobileapps.umbrella.models.watherofweek.List weather = weatherOfTheWeek.get(position);
        holder.tvWeatherDescription.setText(weather.getWeather().get(0).getDescription());
        Picasso.with(context)
                .load(utils.getImageUrl(weather.getWeather().get(0).getIcon()))
                .into(holder.imgWeather);
        holder.tvTime.setText(utils.getJustTIme(weather.getDtTxt())); //No sure
        holder.tvDegree.setText(utils.getDegree(weather.getMain().getTemp()));


        if(sharedPreferences.isFarenheit(context))
        {
            holder.tvCelsius.setTextColor(context.getColor(R.color.colorNoSelect));
            holder.tvFahrenheit.setTextColor(context.getColor(R.color.colorSelect));
        }
        else
        {
            holder.tvCelsius.setTextColor(context.getColor(R.color.colorSelect));
            holder.tvFahrenheit.setTextColor(context.getColor(R.color.colorNoSelect));
        }

        holder.tvCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.setFarenheit(context,false);
                holder.tvCelsius.setTextColor(context.getColor(R.color.colorSelect));
                holder.tvFahrenheit.setTextColor(context.getColor(R.color.colorNoSelect));
                holder.tvDegree.setText(utils.getDegree());
            }
        });

        holder.tvFahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.setFarenheit(context,true);
                holder.tvCelsius.setTextColor(context.getColor(R.color.colorNoSelect));
                holder.tvFahrenheit.setTextColor(context.getColor(R.color.colorSelect));
                holder.tvDegree.setText(utils.getDegree());
            }
        });

        holder.tvDay.setText(utils.getDayOfTheWeek(weather.getDt()));

    }

    @Override
    public int getItemCount() {
        return weatherOfTheWeek.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvWeatherDescription;
        ImageView imgWeather;
        TextView tvTime;
        TextView tvDegree;
        TextView tvCelsius;
        TextView tvFahrenheit;

        TextView tvDay;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeatherDescription = itemView.findViewById(R.id.tvWeatherDescription);
            imgWeather           = itemView.findViewById(R.id.imgWeather);
            tvTime               = itemView.findViewById(R.id.tvTime);
            tvDegree             = itemView.findViewById(R.id.tvWeatherDegree);
            tvCelsius            = itemView.findViewById(R.id.tvCelcious);
            tvFahrenheit         = itemView.findViewById(R.id.tvFarenheit);

            tvDay                = itemView.findViewById(R.id.tvDay);
        }
    }
}
