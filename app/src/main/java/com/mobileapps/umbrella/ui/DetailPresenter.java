package com.mobileapps.umbrella.ui;

import android.util.Log;

import com.mobileapps.umbrella.models.watherofweek.WeatherOfTheWeek;
import com.mobileapps.umbrella.network.NetworkClient;
import com.mobileapps.umbrella.network.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter implements DetailPresenterInterface
{
    DetailViewInterface dvi;
    private String TAG = "DetailPresenter";
    private String zipCode;


    public DetailPresenter(DetailViewInterface mvi,String zipCode)
    {
        this.dvi = mvi;
        this.zipCode = zipCode;
    }

    @Override
    public void getWeatherOfTheWeek()
    {
        getObservable().subscribeWith(getObserver());
    }


    public Observable<WeatherOfTheWeek> getObservable()
    {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getWeaklyWeather(zipCode,"5bf6c7f8208c9d8b5694f3b2a48f3f0a")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<WeatherOfTheWeek> getObserver()
    {
        return new DisposableObserver<WeatherOfTheWeek>() {
            @Override
            public void onNext(WeatherOfTheWeek weatherOfTheWeek) {
                Log.d(TAG,"OnNext"+weatherOfTheWeek.getList());
                dvi.displayWeather(weatherOfTheWeek);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                dvi.displayError("Error fetching Movie Data");

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }



}
