package com.mobileapps.umbrella.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast


import com.mobileapps.umbrella.R
import com.mobileapps.umbrella.adapters.WeathersAdapter
import com.mobileapps.umbrella.models.watherofweek.WeatherOfTheWeek
import com.mobileapps.umbrella.utilities.SharedPreferences
import kotlinx.android.synthetic.main.activity_details_of_the_week.*

import butterknife.BindView
import butterknife.ButterKnife

class DetailsOfTheWeekActivity : AppCompatActivity(), DetailViewInterface {


    private val TAG = "DetailsOfTheWeek"
    lateinit var adapter: RecyclerView.Adapter<*>
    lateinit var detailPresenter: DetailPresenter
    lateinit var sharedPreferences: SharedPreferences
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_of_the_week)

        ButterKnife.bind(this)
        sharedPreferences = SharedPreferences(this)
        context = this
        setupMVP()
        setupViews()
        getWeatherList()
    }

    private fun getWeatherList() {
        detailPresenter.getWeatherOfTheWeek()
    }


    private fun setupMVP() {
        detailPresenter = DetailPresenter(this, sharedPreferences.getZipCode()?:"16066")
    }

    private fun setupViews() {
        rvWeathers.layoutManager = LinearLayoutManager(this)
    }


    override fun displayWeather(weatherOfTheWeek: WeatherOfTheWeek?) {
        if (weatherOfTheWeek != null) {
            adapter = WeathersAdapter(weatherOfTheWeek.list, context)
            rvWeathers!!.adapter = adapter
        } else {
            Log.d(TAG, "Weather response null")
        }

    }

    override fun displayError(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }
}
