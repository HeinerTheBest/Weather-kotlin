package com.mobileapps.umbrella.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.mobileapps.umbrella.R
import com.mobileapps.umbrella.models.currentWeather.CurrentWeather
import com.mobileapps.umbrella.utilities.SharedPreferences
import com.mobileapps.umbrella.utilities.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity(), MainViewInterface {

    /*@BindView(R.id.tvTitle)
    internal var tvTitle: TextView? = null

    @BindView(R.id.tvWeatherDegree)
    internal var tvDegree: TextView? = null

    @BindView(R.id.tvWeekDay)
    internal var tvWeekDay: TextView? = null

    @BindView(R.id.tvCelcious)
    internal var tvCelcius: TextView? = null

    @BindView(R.id.tvFarenheit)
    internal var tvFarenheit: TextView? = null


    @BindView(R.id.tvWeatherDescription)
    internal var tvWeatherDescription: TextView? = null

    @BindView(R.id.etZipCode)
    internal var etZipCode: EditText? = null

    @BindView(R.id.tvHumaditi)
    internal var tvHumaditi: TextView? = null

    @BindView(R.id.tvWindSpeed)
    internal var tvWindSpeed: TextView? = null

    @BindView(R.id.imgWeather)
    internal var imgWeather: ImageView? = null

    @BindView(R.id.clCurrentWeather)
    internal var clCurrentWeather: ConstraintLayout? = null

    @BindView(R.id.tvSearch)
    internal var tvSearch: TextView? = null*/


    private val TAG = "MainActivity"
    lateinit var mainPresenter : MainPresenter
    private lateinit var utils : Utils
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        utils = Utils(this)
        sharedPreferences = SharedPreferences(this)

        ButterKnife.bind(this)

        etZipCode.setText(sharedPreferences.getZipCode())

        if (etZipCode.text.toString() != "") {
            validateZipCode()
            tvWeekDay.text = utils.currentDayWithDate
        }


    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.tvSearch -> {
                tvSearch!!.visibility = View.INVISIBLE
                validateZipCode()
            }

            R.id.tvCelcious -> {
                sharedPreferences.setFahrenheit(false)
                tvCelcious.setTextColor(getColor(R.color.colorSelect))
                tvFarenheit.setTextColor(getColor(R.color.colorNoSelect))
                tvWeatherDegree.text = utils.degree
            }

            R.id.tvFarenheit -> {
                sharedPreferences.setFahrenheit(true)
                tvCelcious.setTextColor(getColor(R.color.colorNoSelect))
                tvFarenheit!!.setTextColor(getColor(R.color.colorSelect))
                tvWeatherDegree.text = utils.degree
            }

            R.id.tvGoToWeatherForDays -> {
                val intent = Intent(this, DetailsOfTheWeekActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun validateZipCode() {
        if (etZipCode!!.text.toString() != "") {
            setupMVP()
            setupMVP()
            getWeather()
            sharedPreferences.setZipCode(etZipCode!!.text.toString())
            tvWeekDay!!.text = utils.currentDayWithDate
            //Todo keep the information for search
            setWeatherWithRightDegree()

        } else {
            Toast.makeText(this, "Please enter the Zip Code", Toast.LENGTH_SHORT).show()
            tvSearch!!.visibility = View.VISIBLE
        }
    }

    private fun setWeatherWithRightDegree() {
        if (sharedPreferences.isFahrenheit()!!) {
            tvCelcious!!.setTextColor(getColor(R.color.colorNoSelect))
            tvFarenheit!!.setTextColor(getColor(R.color.colorSelect))
        } else {
            tvCelcious!!.setTextColor(getColor(R.color.colorSelect))
            tvFarenheit!!.setTextColor(getColor(R.color.colorNoSelect))
        }
    }

    private fun setupMVP() {
        mainPresenter = MainPresenter(this, etZipCode!!.text.toString())
    }

    private fun getWeather() {
        mainPresenter.getCurrentWeather()
    }

    override fun  displayWeather(currentWeather: CurrentWeather?) {
        if (currentWeather != null) {
            val title = currentWeather.name + "," + etZipCode!!.text.toString()
            tvTitle!!.text = title


            sharedPreferences.setDegree( currentWeather.main!!.temp!!)
            tvWeatherDegree!!.text = utils.degree
            tvHumaditi!!.text = utils.getHumedity(currentWeather.main!!.humidity)
            tvWindSpeed!!.text = utils.getWindSpeed(currentWeather.wind!!.speed!!)
            tvWeatherDescription!!.text = currentWeather.weather!![0].description
            Picasso.with(this)
                    .load(currentWeather.weather!![0].icon?.let { utils.getImageUrl(it) })
                    .into(imgWeather)
            clCurrentWeather!!.visibility = View.VISIBLE
        } else {
            Log.d(TAG, "Movies response null")
        }
        tvSearch!!.visibility = View.VISIBLE
    }



    override fun displayError(s: String) {
        Toast.makeText(this, "Try to insert the zip code again", Toast.LENGTH_SHORT).show()
        //todo add clean the edittext
        tvSearch!!.visibility = View.VISIBLE
    }
}
