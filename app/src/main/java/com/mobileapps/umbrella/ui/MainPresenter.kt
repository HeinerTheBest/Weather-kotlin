package com.mobileapps.umbrella.ui

import android.util.Log

import com.mobileapps.umbrella.models.currentWeather.CurrentWeather
import com.mobileapps.umbrella.network.NetworkClient
import com.mobileapps.umbrella.network.NetworkInterface

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter(internal var mvi: MainViewInterface, private val zipCode: String) : MainPresenterInterface {
    private val TAG = "MainPresenter"


    val observable: Observable<CurrentWeather>
        get() = NetworkClient.getRetrofit()?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getCurrentWeather(zipCode, "5bf6c7f8208c9d8b5694f3b2a48f3f0a")
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())!!

    val observer: DisposableObserver<CurrentWeather>
        get() = object : DisposableObserver<CurrentWeather>() {
            override fun onNext(currentWeather: CurrentWeather) {
                mvi.displayWeather(currentWeather)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "Error$e")
                e.printStackTrace()
                mvi.displayError("Error fetching Movie Data")
            }

            override fun onComplete() {
                Log.d(TAG, "Completed")
            }
        }


    override fun getCurrentWeather() {
        observable.subscribeWith(observer)
    }


}
