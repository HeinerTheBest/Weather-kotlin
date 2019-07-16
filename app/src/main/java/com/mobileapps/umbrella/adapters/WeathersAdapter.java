package com.mobileapps.umbrella.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mobileapps.umbrella.R;
import com.mobileapps.umbrella.models.watherofweek.WeatherOfTheWeek;

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
        com.mobileapps.umbrella.models.watherofweek.List weather = weatherOfTheWeek.get(position);
        holder.tvWeatherDescription.setText(weather.getWeather().get(0).getDescription());

    }

    @Override
    public int getItemCount() {
        return weatherOfTheWeek.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvWeatherDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeatherDescription = itemView.findViewById(R.id.tvWeatherDescription);
        }
    }
}
