package com.mobileapps.umbrella.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


import com.mobileapps.umbrella.R
import com.mobileapps.umbrella.utilities.SharedPreferences
import com.mobileapps.umbrella.utilities.Utils
import com.squareup.picasso.Picasso

class WeathersAdapter(internal var weatherOfTheWeek: List<com.mobileapps.umbrella.models.watherofweek.List>?, internal var context: Context) : RecyclerView.Adapter<WeathersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_weather, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val utils = Utils(context)
        val sharedPreferences = SharedPreferences(context)
        val weather = weatherOfTheWeek?.get(position)
        holder.tvWeatherDescription.text = weather?.weather!![0].description
        Picasso.with(context)
                .load(utils.getImageUrl(weather.weather!![0].icon?:""))
                .into(holder.imgWeather)
        holder.tvTime.text = utils.getJustTIme(weather.dtTxt?:"") //No sure
        holder.tvDegree.text = utils.getDegree(weather.main!!.temp)


        if (sharedPreferences.isFahrenheit()!!) {
            holder.tvCelsius.setTextColor(context.getColor(R.color.colorNoSelect))
            holder.tvFahrenheit.setTextColor(context.getColor(R.color.colorSelect))
        } else {
            holder.tvCelsius.setTextColor(context.getColor(R.color.colorSelect))
            holder.tvFahrenheit.setTextColor(context.getColor(R.color.colorNoSelect))
        }

        holder.tvCelsius.setOnClickListener {
            sharedPreferences.setFahrenheit(false)
            holder.tvCelsius.setTextColor(context.getColor(R.color.colorSelect))
            holder.tvFahrenheit.setTextColor(context.getColor(R.color.colorNoSelect))
            holder.tvDegree.text = utils.degree
        }

        holder.tvFahrenheit.setOnClickListener {
            sharedPreferences.setFahrenheit(true)
            holder.tvCelsius.setTextColor(context.getColor(R.color.colorNoSelect))
            holder.tvFahrenheit.setTextColor(context.getColor(R.color.colorSelect))
            holder.tvDegree.text = utils.degree
        }

        holder.tvDay.text = utils.getDayOfTheWeek(weather.dt)

    }

    override fun getItemCount(): Int {
        return weatherOfTheWeek?.size?:0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvWeatherDescription: TextView
        internal var imgWeather: ImageView
        internal var tvTime: TextView
        internal var tvDegree: TextView
        internal var tvCelsius: TextView
        internal var tvFahrenheit: TextView

        internal var tvDay: TextView


        init {
            tvWeatherDescription = itemView.findViewById(R.id.tvWeatherDescription)
            imgWeather = itemView.findViewById(R.id.imgWeather)
            tvTime = itemView.findViewById(R.id.tvTime)
            tvDegree = itemView.findViewById(R.id.tvWeatherDegree)
            tvCelsius = itemView.findViewById(R.id.tvCelcious)
            tvFahrenheit = itemView.findViewById(R.id.tvFarenheit)

            tvDay = itemView.findViewById(R.id.tvDay)
        }
    }
}
