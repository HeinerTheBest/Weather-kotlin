package com.mobileapps.umbrella.ui

import android.util.Log

import com.mobileapps.umbrella.models.watherofweek.WeatherOfTheWeek
import com.mobileapps.umbrella.network.NetworkClient
import com.mobileapps.umbrella.network.NetworkInterface

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class DetailPresenter(internal var dvi: DetailViewInterface, private val zipCode: String) : DetailPresenterInterface {
    private val TAG = "DetailPresenter"


    val observable: Observable<WeatherOfTheWeek>
        get() = NetworkClient.getRetrofit()!!.create<NetworkInterface>(NetworkInterface::class.java)
                .getWeaklyWeather(zipCode, "5bf6c7f8208c9d8b5694f3b2a48f3f0a")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    val observer: DisposableObserver<WeatherOfTheWeek>
        get() = object : DisposableObserver<WeatherOfTheWeek>() {
            override fun onNext(weatherOfTheWeek: WeatherOfTheWeek) {
                Log.d(TAG, "OnNext" + weatherOfTheWeek.list!!)
                dvi.displayWeather(weatherOfTheWeek)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "Error$e")
                e.printStackTrace()
                dvi.displayError("Error fetching Movie Data")

            }

            override fun onComplete() {
                Log.d(TAG, "Completed")
            }
        }

    override fun getWeatherOfTheWeek() {
        observable.subscribeWith(observer)
    }


}
